package ca.bc.gov.open.pcss.controllers;

import ca.bc.gov.open.pcss.configuration.SoapConfig;
import ca.bc.gov.open.pcss.exceptions.BadDateException;
import ca.bc.gov.open.pcss.exceptions.ORDSException;
import ca.bc.gov.open.pcss.models.OrdsErrorLog;
import ca.bc.gov.open.pcss.models.RequestSuccessLog;
import ca.bc.gov.open.pcss.three.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

@Endpoint
@Slf4j
public class AppearanceController {

    @Value("${pcss.host}")
    private String host = "https://127.0.0.1/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public AppearanceController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivil")
    @ResponsePayload
    public GetAppearanceCivilResponse getAppearanceCivil(@RequestPayload GetAppearanceCivil search)
            throws JsonProcessingException, BadDateException {
        addEndpointHeader("getAppearanceCivil");
        var inner =
                search.getGetAppearanceCivilRequest() != null
                                && search.getGetAppearanceCivilRequest()
                                                .getGetAppearanceCivilRequest()
                                        != null
                        ? search.getGetAppearanceCivilRequest().getGetAppearanceCivilRequest()
                        : new ca.bc.gov.open.pcss.one.GetAppearanceCivilRequest();

        if (inner.getRequestDtm() == null) {
            log.warn(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Bad date format or missing date received",
                                    "getAppearanceCivil",
                                    "",
                                    search)));
            throw new BadDateException();
        }

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "appearance")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("physicalFileId", inner.getPhysicalFileId())
                        .queryParam("futureYN", inner.getFutureYN())
                        .queryParam("historyYN", inner.getHistoryYN());

        try {
            HttpEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.one.GetAppearanceCivilResponse.class);

            var out = new GetAppearanceCivilResponse();
            var one = new GetAppearanceCivilResponse2();
            one.setGetAppearanceCivilResponse(resp.getBody());
            out.setGetAppearanceCivilResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getAppearanceCivil")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivil",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "setAppearanceCivil")
    @ResponsePayload
    public SetAppearanceCivilResponse setAppearanceCivil(@RequestPayload SetAppearanceCivil payload)
            throws JsonProcessingException {
        addEndpointHeader("setAppearanceCivil");
        ca.bc.gov.open.pcss.one.SetAppearanceCivilRequest inner =
                payload.getSetAppearanceCivilRequest() != null
                                && payload.getSetAppearanceCivilRequest()
                                                .getSetAppearanceCivilRequest()
                                        != null
                        ? payload.getSetAppearanceCivilRequest().getSetAppearanceCivilRequest()
                        : new ca.bc.gov.open.pcss.one.SetAppearanceCivilRequest();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "appearance");

        HttpEntity<ca.bc.gov.open.pcss.one.SetAppearanceCivilRequest> body =
                new HttpEntity<>(inner, new HttpHeaders());
        try {
            HttpEntity<ca.bc.gov.open.pcss.one.SetAppearanceCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            body,
                            ca.bc.gov.open.pcss.one.SetAppearanceCivilResponse.class);
            var out = new SetAppearanceCivilResponse();
            var one = new SetAppearanceCivilResponse2();
            one.setSetAppearanceCivilResponse(resp.getBody());
            out.setSetAppearanceCivilResponse(one);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "setAppearanceCivil")));
            return out;

        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "setAppearanceCivil",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilApprMethod")
    @ResponsePayload
    public GetAppearanceCivilApprMethodResponse getAppearanceCivilApprMethod(
            @RequestPayload GetAppearanceCivilApprMethod search)
            throws JsonProcessingException, BadDateException {
        addEndpointHeader("getAppearanceCivilApprMethod");
        var inner =
                search.getGetAppearanceCivilApprMethodRequest() != null
                                && search.getGetAppearanceCivilApprMethodRequest()
                                                .getGetAppearanceCivilApprMethodRequest()
                                        != null
                        ? search.getGetAppearanceCivilApprMethodRequest()
                                .getGetAppearanceCivilApprMethodRequest()
                        : new ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodRequest();

        if (inner.getRequestDtm() == null) {
            log.warn(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Bad date format or missing date received",
                                    "getAppearanceCivilApprMethod",
                                    "",
                                    search)));
            throw new BadDateException();
        }

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "appearance/appearance-method")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("appearanceId", inner.getAppearanceId());

        try {
            HttpEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodResponse.class);

            var out = new GetAppearanceCivilApprMethodResponse();
            var one = new GetAppearanceCivilApprMethodResponse2();
            one.setGetAppearanceCivilApprMethodResponse(resp.getBody());
            out.setGetAppearanceCivilApprMethodResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success", "getAppearanceCivilApprMethod")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilApprMethod",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "setAppearanceMethodCivil")
    @ResponsePayload
    public SetAppearanceMethodCivilResponse setAppearanceMethodCivil(
            @RequestPayload SetAppearanceMethodCivil payload)
            throws JsonProcessingException, BadDateException {
        addEndpointHeader("setAppearanceMethodCivil");
        var inner =
                payload.getSetAppearanceMethodCivilRequest() != null
                                && payload.getSetAppearanceMethodCivilRequest()
                                                .getSetAppearanceMethodCivilRequest()
                                        != null
                        ? payload.getSetAppearanceMethodCivilRequest()
                                .getSetAppearanceMethodCivilRequest()
                        : new ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilRequest();

        if (inner.getRequestDtm() == null) {
            log.warn(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Bad date format or missing date received",
                                    "setAppearanceMethodCivil",
                                    "",
                                    payload)));
            throw new BadDateException();
        }

        HttpEntity<ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilRequest> body =
                new HttpEntity<>(inner, new HttpHeaders());

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "appearance/appearance-method");
        try {
            HttpEntity<ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            body,
                            ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilResponse.class);

            var out = new SetAppearanceMethodCivilResponse();
            var one = new SetAppearanceMethodCivilResponse2();
            one.setSetAppearanceMethodCivilResponse(resp.getBody());
            out.setSetAppearanceMethodCivilResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "setAppearanceMethodCivil")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "setAppearanceMethodCivil",
                                    ex.getMessage(),
                                    payload)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilDocument")
    @ResponsePayload
    public GetAppearanceCivilDocumentResponse getAppearanceCivilDocument(
            @RequestPayload GetAppearanceCivilDocument search) throws JsonProcessingException {
        addEndpointHeader("getAppearanceCivilDocument");
        var inner =
                search.getGetAppearanceCivilDocumentRequest() != null
                                && search.getGetAppearanceCivilDocumentRequest()
                                                .getGetAppearanceCivilDocumentRequest()
                                        != null
                        ? search.getGetAppearanceCivilDocumentRequest()
                                .getGetAppearanceCivilDocumentRequest()
                        : new ca.bc.gov.open.pcss.one.GetAppearanceCivilDocumentRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "appearance/document")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("appearanceId", inner.getAppearanceId());

        try {
            HttpEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilDocumentResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.one.GetAppearanceCivilDocumentResponse.class);

            var out = new GetAppearanceCivilDocumentResponse();
            var one = new GetAppearanceCivilDocumentResponse2();
            one.setGetAppearanceCivilDocumentResponse(resp.getBody());
            out.setGetAppearanceCivilDocumentResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success", "getAppearanceCivilDocument")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilDocument",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilParty")
    @ResponsePayload
    public GetAppearanceCivilPartyResponse getAppearanceCivilParty(
            @RequestPayload GetAppearanceCivilParty search) throws JsonProcessingException {
        addEndpointHeader("getAppearanceCivilParty");
        var inner =
                search.getGetAppearanceCivilPartyRequest() != null
                                && search.getGetAppearanceCivilPartyRequest()
                                                .getGetAppearanceCivilPartyRequest()
                                        != null
                        ? search.getGetAppearanceCivilPartyRequest()
                                .getGetAppearanceCivilPartyRequest()
                        : new ca.bc.gov.open.pcss.one.GetAppearanceCivilPartyRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "appearance/party")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("appearanceId", inner.getAppearanceId());

        try {
            HttpEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilPartyResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.one.GetAppearanceCivilPartyResponse.class);

            var out = new GetAppearanceCivilPartyResponse();
            var one = new GetAppearanceCivilPartyResponse2();
            one.setGetAppearanceCivilPartyResponse(resp.getBody());
            out.setGetAppearanceCivilPartyResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getAppearanceCivilParty")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilParty",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilResource")
    @ResponsePayload
    public GetAppearanceCivilResourceResponse getAppearanceCivilResource(
            @RequestPayload GetAppearanceCivilResource search) throws JsonProcessingException {
        addEndpointHeader("getAppearanceCivilResource");
        var inner =
                search.getGetAppearanceCivilResourceRequest() != null
                                && search.getGetAppearanceCivilResourceRequest()
                                                .getGetAppearanceCivilResourceRequest()
                                        != null
                        ? search.getGetAppearanceCivilResourceRequest()
                                .getGetAppearanceCivilResourceRequest()
                        : new ca.bc.gov.open.pcss.one.GetAppearanceCivilResourceRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "appearance/resource")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("appearanceId", inner.getAppearanceId());

        try {
            HttpEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilResourceResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.one.GetAppearanceCivilResourceResponse.class);

            var out = new GetAppearanceCivilResourceResponse();
            var one = new GetAppearanceCivilResourceResponse2();
            one.setGetAppearanceCivilResourceResponse(resp.getBody());
            out.setGetAppearanceCivilResourceResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success", "getAppearanceCivilResource")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilResource",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "setCounselDetailCivil")
    @ResponsePayload
    public SetCounselDetailCivilResponse setCounselDetailCivil(
            @RequestPayload SetCounselDetailCivil payload) throws JsonProcessingException {
        addEndpointHeader("setCounselDetailCivil");
        var inner =
                payload.getSetCounselDetailCivilRequest() != null
                                && payload.getSetCounselDetailCivilRequest()
                                                .getSetCounselDetailCivilRequest()
                                        != null
                        ? payload.getSetCounselDetailCivilRequest()
                                .getSetCounselDetailCivilRequest()
                        : new ca.bc.gov.open.pcss.one.SetCounselDetailCivilRequest();

        HttpEntity<ca.bc.gov.open.pcss.one.SetCounselDetailCivilRequest> body =
                new HttpEntity<>(inner, new HttpHeaders());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "counsel");
        try {
            HttpEntity<ca.bc.gov.open.pcss.one.SetCounselDetailCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            body,
                            ca.bc.gov.open.pcss.one.SetCounselDetailCivilResponse.class);

            var finalOut = new SetCounselDetailCivilResponse();
            var one = new SetCounselDetailCivilResponse2();
            one.setSetCounselDetailCivilResponse(resp.getBody());
            finalOut.setSetCounselDetailCivilResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "setCounselDetailCivil")));
            return finalOut;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "setCounselDetailCivil",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    private void addEndpointHeader(String endpoint) {
        try {
            TransportContext context = TransportContextHolder.getTransportContext();
            HttpServletConnection connection = (HttpServletConnection) context.getConnection();
            connection.addResponseHeader("Endpoint", endpoint);
        } catch (Exception ex) {
            log.warn("Failed to add endpoint response header");
        }
    }
}
