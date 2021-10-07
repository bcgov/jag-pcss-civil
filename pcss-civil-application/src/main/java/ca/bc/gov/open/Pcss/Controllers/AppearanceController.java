package ca.bc.gov.open.Pcss.Controllers;

import ca.bc.gov.open.Pcss.Configuration.SoapConfig;
import ca.bc.gov.open.Pcss.Exceptions.ORDSException;
import ca.bc.gov.open.Pcss.Models.OrdsErrorLog;
import com.example.demp.wsdl.pcss.three.*;
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
            throws JsonProcessingException {

        var inner =
                search.getGetAppearanceCivilRequest() != null
                                && search.getGetAppearanceCivilRequest()
                                                .getGetAppearanceCivilRequest()
                                        != null
                        ? search.getGetAppearanceCivilRequest().getGetAppearanceCivilRequest()
                        : new com.example.demp.wsdl.pcss.one.GetAppearanceCivilRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "appearance")
                        .queryParam(
                                "RequestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("PhysicalFileId", inner.getPhysicalFileId())
                        .queryParam("FutureYN", inner.getFutureYN())
                        .queryParam("HistoryYN", inner.getHistoryYN());

        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.one.GetAppearanceCivilResponse.class);

            var out = new GetAppearanceCivilResponse();
            var one = new GetAppearanceCivilResponse2();
            one.setGetAppearanceCivilResponse(resp.getBody());
            out.setGetAppearanceCivilResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "SaveHearingResult", inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "setAppearanceCivil")
    @ResponsePayload
    public SetAppearanceCivilResponse setAppearanceCivil(@RequestPayload SetAppearanceCivil payload)
            throws JsonProcessingException {

        com.example.demp.wsdl.pcss.one.SetAppearanceCivilRequest inner =
                payload.getSetAppearanceCivilRequest() != null
                                && payload.getSetAppearanceCivilRequest()
                                                .getSetAppearanceCivilRequest()
                                        != null
                        ? payload.getSetAppearanceCivilRequest().getSetAppearanceCivilRequest()
                        : new com.example.demp.wsdl.pcss.one.SetAppearanceCivilRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "SetAppearanceCivil");

        HttpEntity<com.example.demp.wsdl.pcss.one.SetAppearanceCivilRequest> body =
                new HttpEntity<>(inner, new HttpHeaders());
        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.SetAppearanceCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            body,
                            com.example.demp.wsdl.pcss.one.SetAppearanceCivilResponse.class);
            var out = new SetAppearanceCivilResponse();
            var one = new SetAppearanceCivilResponse2();
            one.setSetAppearanceCivilResponse(resp.getBody());
            out.setSetAppearanceCivilResponse(one);

            return out;

        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "SaveHearingResult", inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilApprMethod")
    @ResponsePayload
    public GetAppearanceCivilApprMethodResponse getAppearanceCivilApprMethod(
            @RequestPayload GetAppearanceCivilApprMethod search) throws JsonProcessingException {

        var inner =
                search.getGetAppearanceCivilApprMethodRequest() != null
                                && search.getGetAppearanceCivilApprMethodRequest()
                                                .getGetAppearanceCivilApprMethodRequest()
                                        != null
                        ? search.getGetAppearanceCivilApprMethodRequest()
                                .getGetAppearanceCivilApprMethodRequest()
                        : new com.example.demp.wsdl.pcss.one.GetAppearanceCivilApprMethodRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "GetAppearanceCivilApprMethod")
                        .queryParam(
                                "RequestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("AppearanceId", inner.getAppearanceId());

        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilApprMethodResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.one.GetAppearanceCivilApprMethodResponse
                                    .class);

            var out = new GetAppearanceCivilApprMethodResponse();
            var one = new GetAppearanceCivilApprMethodResponse2();
            one.setGetAppearanceCivilApprMethodResponse(resp.getBody());
            out.setGetAppearanceCivilApprMethodResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "SaveHearingResult", inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "setAppearanceMethodCivil")
    @ResponsePayload
    public SetAppearanceMethodCivilResponse setAppearanceMethodCivil(
            @RequestPayload SetAppearanceMethodCivil payload) throws JsonProcessingException {

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "setAppearanceMethodCivil");
        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.SetAppearanceMethodCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.one.SetAppearanceMethodCivilResponse.class);

            var out = new SetAppearanceMethodCivilResponse();
            var one = new SetAppearanceMethodCivilResponse2();
            one.setSetAppearanceMethodCivilResponse(resp.getBody());
            out.setSetAppearanceMethodCivilResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "SaveHearingResult", payload)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilDocument")
    @ResponsePayload
    public GetAppearanceCivilDocumentResponse getAppearanceCivilDocument(
            @RequestPayload GetAppearanceCivilDocument search) throws JsonProcessingException {

        var inner =
                search.getGetAppearanceCivilDocumentRequest() != null
                                && search.getGetAppearanceCivilDocumentRequest()
                                                .getGetAppearanceCivilDocumentRequest()
                                        != null
                        ? search.getGetAppearanceCivilDocumentRequest()
                                .getGetAppearanceCivilDocumentRequest()
                        : new com.example.demp.wsdl.pcss.one.GetAppearanceCivilDocumentRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "GetAppearanceCivilDocument")
                        .queryParam(
                                "RequestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("AppearanceId", inner.getAppearanceId());

        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilDocumentResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.one.GetAppearanceCivilDocumentResponse
                                    .class);

            var out = new GetAppearanceCivilDocumentResponse();
            var one = new GetAppearanceCivilDocumentResponse2();
            one.setGetAppearanceCivilDocumentResponse(resp.getBody());
            out.setGetAppearanceCivilDocumentResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "SaveHearingResult", inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilParty")
    @ResponsePayload
    public GetAppearanceCivilPartyResponse getAppearanceCivilParty(
            @RequestPayload GetAppearanceCivilParty search) throws JsonProcessingException {

        var inner =
                search.getGetAppearanceCivilPartyRequest() != null
                                && search.getGetAppearanceCivilPartyRequest()
                                                .getGetAppearanceCivilPartyRequest()
                                        != null
                        ? search.getGetAppearanceCivilPartyRequest()
                                .getGetAppearanceCivilPartyRequest()
                        : new com.example.demp.wsdl.pcss.one.GetAppearanceCivilPartyRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "GetAppearanceCivilParty")
                        .queryParam(
                                "RequestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("AppearanceId", inner.getAppearanceId());

        HttpEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilPartyResponse> resp =
                restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.GET,
                        new HttpEntity<>(new HttpHeaders()),
                        com.example.demp.wsdl.pcss.one.GetAppearanceCivilPartyResponse.class);

        try {
            var out = new GetAppearanceCivilPartyResponse();
            var one = new GetAppearanceCivilPartyResponse2();
            one.setGetAppearanceCivilPartyResponse(resp.getBody());
            out.setGetAppearanceCivilPartyResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "SaveHearingResult", inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilResource")
    @ResponsePayload
    public GetAppearanceCivilResourceResponse getAppearanceCivilResource(
            @RequestPayload GetAppearanceCivilResource search) throws JsonProcessingException {

        var inner =
                search.getGetAppearanceCivilResourceRequest() != null
                                && search.getGetAppearanceCivilResourceRequest()
                                                .getGetAppearanceCivilResourceRequest()
                                        != null
                        ? search.getGetAppearanceCivilResourceRequest()
                                .getGetAppearanceCivilResourceRequest()
                        : new com.example.demp.wsdl.pcss.one.GetAppearanceCivilResourceRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "GetAppearanceCivilResource")
                        .queryParam(
                                "RequestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("AppearanceId", inner.getAppearanceId());

        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilResourceResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.one.GetAppearanceCivilResourceResponse
                                    .class);

            var out = new GetAppearanceCivilResourceResponse();
            var one = new GetAppearanceCivilResourceResponse2();
            one.setGetAppearanceCivilResourceResponse(resp.getBody());
            out.setGetAppearanceCivilResourceResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "SaveHearingResult", inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "setCounselDetailCivil")
    @ResponsePayload
    public SetCounselDetailCivilResponse setCounselDetailCivil(
            @RequestPayload SetCounselDetailCivil payload) throws JsonProcessingException {

        var inner =
                payload.getSetCounselDetailCivilRequest() != null
                                && payload.getSetCounselDetailCivilRequest()
                                                .getSetCounselDetailCivilRequest()
                                        != null
                        ? payload.getSetCounselDetailCivilRequest()
                                .getSetCounselDetailCivilRequest()
                        : new com.example.demp.wsdl.pcss.one.SetCounselDetailCivilRequest();

        HttpEntity<com.example.demp.wsdl.pcss.one.SetCounselDetailCivilRequest> body =
                new HttpEntity<>(inner, new HttpHeaders());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "civil/counsel");
        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.SetCounselDetailCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            body,
                            com.example.demp.wsdl.pcss.one.SetCounselDetailCivilResponse.class);

            var finalOut = new SetCounselDetailCivilResponse();
            var one = new SetCounselDetailCivilResponse2();
            one.setSetCounselDetailCivilResponse(resp.getBody());
            finalOut.setSetCounselDetailCivilResponse(one);
            return finalOut;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "SaveHearingResult", inner)));
            throw new ORDSException();
        }
    }
}
