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
public class SyncController {
    @Value("${pcss.host}")
    private String host = "https://127.0.0.1/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SyncController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getSyncCivilAppearance")
    @ResponsePayload
    public GetSyncCivilAppearanceResponse getSyncCivilAppearance(
            @RequestPayload GetSyncCivilAppearance search) throws JsonProcessingException {

        var inner =
                search.getGetSyncCivilAppearanceRequest() != null
                                && search.getGetSyncCivilAppearanceRequest()
                                                .getGetSyncCivilAppearanceRequest()
                                        != null
                        ? search.getGetSyncCivilAppearanceRequest()
                                .getGetSyncCivilAppearanceRequest()
                        : new com.example.demp.wsdl.pcss.one.GetSyncCivilAppearanceRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "GetSyncCivilAppearance")
                        .queryParam(
                                "RequestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("ProcessUpToDtm", inner.getProcessUpToDtm());

        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.GetSyncCivilAppearanceResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.one.GetSyncCivilAppearanceResponse.class);

            var out = new GetSyncCivilAppearanceResponse();
            var one = new GetSyncCivilAppearanceResponse2();
            one.setGetSyncCivilAppearanceResponse(resp.getBody());
            out.setGetSyncCivilAppearanceResponse(one);

            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "getSyncCivilAppearance", inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(
            namespace = SoapConfig.SOAP_NAMESPACE,
            localPart = "getSyncCivilHearingRestriction")
    @ResponsePayload
    public GetSyncCivilHearingRestrictionResponse getSyncCivilHearingRestriction(
            @RequestPayload GetSyncCivilHearingRestriction search) throws JsonProcessingException {

        var inner =
                search.getGetSyncCivilHearingRestrictionRequest() != null
                                && search.getGetSyncCivilHearingRestrictionRequest()
                                                .getGetSyncCivilHearingRestrictionRequest()
                                        != null
                        ? search.getGetSyncCivilHearingRestrictionRequest()
                                .getGetSyncCivilHearingRestrictionRequest()
                        : new com.example.demp.wsdl.pcss.one
                                .GetSyncCivilHearingRestrictionRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "GetSyncCivilHearingRestriction")
                        .queryParam(
                                "RequestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("ProcessUpToDtm", inner.getProcessUpToDtm());

        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.GetSyncCivilHearingRestrictionResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.one.GetSyncCivilHearingRestrictionResponse
                                    .class);

            var out = new GetSyncCivilHearingRestrictionResponse();
            var one = new GetSyncCivilHearingRestrictionResponse2();
            one.setGetSyncCivilHearingRestrictionResponse(resp.getBody());
            out.setGetSyncCivilHearingRestrictionResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getSyncCivilHearingRestriction",
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "setHearingRestrictionCivil")
    @ResponsePayload
    public SetHearingRestrictionCivilResponse setSyncCivilHearingRestriction(
            @RequestPayload SetHearingRestrictionCivil search) throws JsonProcessingException {

        var inner =
                search.getSetHearingRestrictionCivilRequest() != null
                                && search.getSetHearingRestrictionCivilRequest()
                                                .getSetHearingRestrictionCivilRequest()
                                        != null
                        ? search.getSetHearingRestrictionCivilRequest()
                                .getSetHearingRestrictionCivilRequest()
                        : new com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "hearing-restriction");

        HttpEntity<com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilRequest> body =
                new HttpEntity<>(inner, new HttpHeaders());
        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            body,
                            com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilResponse
                                    .class);

            var out = new SetHearingRestrictionCivilResponse();
            var one = new SetHearingRestrictionCivilResponse2();
            one.setSetHearingRestrictionCivilResponse(resp.getBody());
            out.setSetHearingRestrictionCivilResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "setHearingRestrictionCivil",
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getFileDetailCivil")
    @ResponsePayload
    public GetFileDetailCivilResponse getFileDetailCivil(@RequestPayload GetFileDetailCivil search)
            throws JsonProcessingException {

        var inner =
                search.getGetFileDetailCivilRequest() != null
                                && search.getGetFileDetailCivilRequest()
                                                .getGetFileDetailCivilRequest()
                                        != null
                        ? search.getGetFileDetailCivilRequest().getGetFileDetailCivilRequest()
                        : new com.example.demp.wsdl.pcss.one.GetFileDetailCivilRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "file-detail")
                        .queryParam(
                                "requestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("physicalFileId", inner.getPhysicalFileId());

        try {
            HttpEntity<com.example.demp.wsdl.pcss.one.GetFileDetailCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.one.GetFileDetailCivilResponse.class);

            var out = new GetFileDetailCivilResponse();
            var one = new GetFileDetailCivilResponse2();
            one.setGetFileDetailCivilResponse(resp.getBody());
            out.setGetFileDetailCivilResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS", "GetFileDetailCivil", inner)));
            throw new ORDSException();
        }
    }
}
