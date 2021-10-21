package ca.bc.gov.open.pcss.controllers;

import ca.bc.gov.open.pcss.configuration.SoapConfig;
import ca.bc.gov.open.pcss.exceptions.ORDSException;
import ca.bc.gov.open.pcss.models.OrdsErrorLog;
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
                        : new ca.bc.gov.open.pcss.one.GetSyncCivilAppearanceRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "sync/appearance")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("ProcessUpToDtm", inner.getProcessUpToDtm());

        try {
            HttpEntity<ca.bc.gov.open.pcss.one.GetSyncCivilAppearanceResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.one.GetSyncCivilAppearanceResponse.class);

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
                        : new ca.bc.gov.open.pcss.one.GetSyncCivilHearingRestrictionRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "hearing-restriction")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("ProcessUpToDtm", inner.getProcessUpToDtm());

        try {
            HttpEntity<ca.bc.gov.open.pcss.one.GetSyncCivilHearingRestrictionResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.one.GetSyncCivilHearingRestrictionResponse.class);

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
                        : new ca.bc.gov.open.pcss.one.SetHearingRestrictionCivilRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "hearing-restriction");

        HttpEntity<ca.bc.gov.open.pcss.one.SetHearingRestrictionCivilRequest> body =
                new HttpEntity<>(inner, new HttpHeaders());
        try {
            HttpEntity<ca.bc.gov.open.pcss.one.SetHearingRestrictionCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.POST,
                            body,
                            ca.bc.gov.open.pcss.one.SetHearingRestrictionCivilResponse.class);

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
                        : new ca.bc.gov.open.pcss.one.GetFileDetailCivilRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "file-detail")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("physicalFileId", inner.getPhysicalFileId());

        try {
            HttpEntity<ca.bc.gov.open.pcss.one.GetFileDetailCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.one.GetFileDetailCivilResponse.class);

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