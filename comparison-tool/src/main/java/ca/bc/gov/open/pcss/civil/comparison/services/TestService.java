package ca.bc.gov.open.pcss.civil.comparison.services;

import ca.bc.gov.open.pcss.civil.comparison.config.DualProtocolSaajSoapMessageFactory;
import ca.bc.gov.open.pcss.civil.comparison.config.WebServiceSenderWithAuth;

import ca.bc.gov.open.pcss.three.GetFileDetailCivil;
import ca.bc.gov.open.pcss.three.GetFileDetailCivilRequest;
import ca.bc.gov.open.pcss.three.GetFileDetailCivilResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class TestService {
    private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    private WebServiceSenderWithAuth webServiceSenderWithAuth;

    @Autowired
    public TestService(WebServiceSenderWithAuth webServiceSenderWithAuth) {
        this.webServiceSenderWithAuth = webServiceSenderWithAuth;
    }

    @Value("${host.api_host}")
    private String apiHost;

    @Value("${host.wm_host}")
    private String wmHost;

    @Value("${host.username}")
    private String username;

    @Value("${host.password}")
    private String password;

    public void runCompare() {
        GetFileDetailCivil request = new GetFileDetailCivil();
        ca.bc.gov.open.pcss.one.GetFileDetailCivilRequest one =
                new ca.bc.gov.open.pcss.one.GetFileDetailCivilRequest();
        one.setPhysicalFileId("3");
        one.setRequestAgencyIdentifierId("83.0001");
        one.setRequestDtm(Instant.now());
        one.setRequestPartId("83.0001");
        GetFileDetailCivilRequest three = new GetFileDetailCivilRequest();
        three.setGetFileDetailCivilRequest(one);
        request.setGetFileDetailCivilRequest(three);

        compare(new GetFileDetailCivilResponse(), request);
    }

    public <T, G> boolean compare(T response, G request) {

        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

        DualProtocolSaajSoapMessageFactory saajSoapMessageFactory =
                new DualProtocolSaajSoapMessageFactory();
        saajSoapMessageFactory.setSoapVersion(SoapVersion.SOAP_12);
        saajSoapMessageFactory.afterPropertiesSet();

        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setCredentials(
                new UsernamePasswordCredentials(username, password));

        webServiceTemplate.setMessageSender(webServiceSenderWithAuth);
        webServiceTemplate.setMessageFactory(saajSoapMessageFactory);
        jaxb2Marshaller.setContextPaths("ca.bc.gov.open.pcss.one", "ca.bc.gov.open.pcss.three");
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        webServiceTemplate.afterPropertiesSet();

        T resultObjectWM = null;
        T resultObjectAPI = null;

        try {
            resultObjectWM = (T) webServiceTemplate.marshalSendAndReceive(wmHost, request);
            resultObjectAPI = (T) webServiceTemplate.marshalSendAndReceive(apiHost, request);

            GetFileDetailCivilResponse getFileDetailCivilResponse = (GetFileDetailCivilResponse) resultObjectAPI;
            getFileDetailCivilResponse.getGetFileDetailCivilResponse().
                    getGetFileDetailCivilResponse().setResponseCd("sds");
            getFileDetailCivilResponse.getGetFileDetailCivilResponse().
                    getGetFileDetailCivilResponse().setCommentToJudgeTxt("API ahahahah");

        } catch (Exception e) {
            System.out.println("ERROR: Failed to send request... " + e);
        }

        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(resultObjectAPI, resultObjectWM);

        if (diff.hasChanges()) {
            printDiff(diff);
            return false;
        } else {
            String responseClassName = response.getClass().getName();
            if (resultObjectAPI == null && resultObjectWM == null)
                System.out.println("WARN: "
                        + responseClassName.substring(responseClassName.lastIndexOf('.') + 1)
                        + ": NULL responses");
            else
                System.out.println("INFO: "
                        + responseClassName.substring(responseClassName.lastIndexOf('.') + 1)
                        + ": No Diff Detected");
            return true;
        }
    }

    private static void printDiff(Diff diff) {
        int diffSize = diff.getChanges().size();
        if (diffSize == 0) return;
        String[] header = new String[] { "Property", "API Response", "WM Response" };
        String[][] table = new String[diffSize + 1][3];
        table[0] = header;

        for (int i = 0; i < diffSize; ++i) {
            Change ch = diff.getChanges().get(i);
            String apiVal =  ((ValueChange) ch).getLeft() == null ?
                    "null" : ((ValueChange) ch).getLeft().toString();
            String wmVal =  ((ValueChange) ch).getRight() == null ?
                    "null" : ((ValueChange) ch).getRight().toString();
            table[i + 1][0] = ((ValueChange) ch).getPropertyNameWithPath();
            table[i + 1][1] = apiVal;
            table[i + 1][2] = wmVal;
        }


        boolean leftJustifiedRows = false;
        int totalColumnLength = 10;
        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));

        for ( Map.Entry<Integer, Integer> e : columnLengths.entrySet() ) {
            totalColumnLength += e.getValue();
        }
        System.out.println("=".repeat(totalColumnLength));

        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");

        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));

        System.out.println("=".repeat(totalColumnLength));
    }
}
