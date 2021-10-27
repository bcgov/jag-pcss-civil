package ca.bc.gov.open.pcss.controllers;

import ca.bc.gov.open.pcss.configuration.SoapConfig;
import ca.bc.gov.open.pcss.exceptions.ORDSException;
import ca.bc.gov.open.pcss.models.OrdsErrorLog;
import ca.bc.gov.open.pcss.models.RequestSuccessLog;
import ca.bc.gov.open.pcss.models.serializers.InstantSerializer;
import ca.bc.gov.open.pcss.secure.two.*;
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
public class SecureEndpointController {

    @Value("${pcss.host}")
    private String host = "https://127.0.0.1/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SecureEndpointController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PayloadRoot(
            namespace = SoapConfig.SOAP_NAMESPACE,
            localPart = "getAppearanceCivilApprMethodSecure")
    @ResponsePayload
    public GetAppearanceCivilApprMethodSecureResponse getAppearanceCivilApprMethodSecureRequest(
            @RequestPayload GetAppearanceCivilApprMethodSecure search)
            throws JsonProcessingException {

        var inner =
                search.getGetAppearanceCivilApprMethodSecureRequest() != null
                                && search.getGetAppearanceCivilApprMethodSecureRequest()
                                                .getGetAppearanceCivilApprMethodSecureRequest()
                                        != null
                        ? search.getGetAppearanceCivilApprMethodSecureRequest()
                                .getGetAppearanceCivilApprMethodSecureRequest()
                        : new ca.bc.gov.open.pcss.secure.one
                                .GetAppearanceCivilApprMethodSecureRequest();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "secure/appearance/appearance-method")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", InstantSerializer.convert(inner.getRequestDtm()))
                        .queryParam("applicationCd", inner.getApplicationCd())
                        .queryParam("appearanceId", inner.getAppearanceId());
        try {
            HttpEntity<ca.bc.gov.open.pcss.secure.one.GetAppearanceCivilApprMethodResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.secure.one.GetAppearanceCivilApprMethodResponse
                                    .class);

            var out = new GetAppearanceCivilApprMethodSecureResponse();
            var one = new GetAppearanceCivilApprMethodResponse();
            one.setGetAppearanceCivilApprMethodResponse(resp.getBody());
            out.setGetAppearanceCivilApprMethodResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success", "getAppearanceCivilApprMethodSecure")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilApprMethodSecure",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilPartySecure")
    @ResponsePayload
    public GetAppearanceCivilPartySecureResponse getAppearanceCivilPartySecure(
            @RequestPayload GetAppearanceCivilPartySecure search) throws JsonProcessingException {
        var inner =
                search.getGetAppearanceCivilPartySecureRequest() != null
                        ? search.getGetAppearanceCivilPartySecureRequest()
                                .getGetAppearanceCivilPartySecureRequest()
                        : new ca.bc.gov.open.pcss.secure.one.GetAppearanceCivilPartySecureRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "secure/appearance/party")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", InstantSerializer.convert(inner.getRequestDtm()))
                        .queryParam("applicationCd", inner.getApplicationCd())
                        .queryParam("appearanceId", inner.getAppearanceId());
        try {
            HttpEntity<ca.bc.gov.open.pcss.secure.one.GetAppearanceCivilPartyResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.secure.one.GetAppearanceCivilPartyResponse.class);

            var out = new GetAppearanceCivilPartySecureResponse();
            var one = new GetAppearanceCivilPartyResponse();
            one.setGetAppearanceCivilPartyResponse(resp.getBody());
            out.setGetAppearanceCivilPartyResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success", "getAppearanceCivilPartySecure")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilPartySecure",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilSecure")
    @ResponsePayload
    public GetAppearanceCivilSecureResponse getAppearanceCivilSecure(
            @RequestPayload GetAppearanceCivilSecure search) throws JsonProcessingException {
        var inner =
                search.getGetAppearanceCivilSecureRequest() != null
                                && search.getGetAppearanceCivilSecureRequest()
                                                .getGetAppearanceCivilSecureRequest()
                                        != null
                        ? search.getGetAppearanceCivilSecureRequest()
                                .getGetAppearanceCivilSecureRequest()
                        : new ca.bc.gov.open.pcss.secure.one.GetAppearanceCivilSecureRequest();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "secure/appearance")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", InstantSerializer.convert(inner.getRequestDtm()))
                        .queryParam("applicationCd", inner.getApplicationCd())
                        .queryParam("physicalFileId", inner.getPhysicalFileId())
                        .queryParam("futureYN", inner.getFutureYN())
                        .queryParam("historyYN", inner.getHistoryYN());
        try {
            HttpEntity<ca.bc.gov.open.pcss.secure.one.GetAppearanceCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.secure.one.GetAppearanceCivilResponse.class);

            var out = new GetAppearanceCivilSecureResponse();
            var one = new GetAppearanceCivilResponse();
            one.setGetAppearanceCivilResponse(resp.getBody());
            out.setGetAppearanceCivilResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getAppearanceCivilSecure")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilSecure",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getFileDetailCivilSecure")
    @ResponsePayload
    public GetFileDetailCivilSecureResponse getFileDetailCivilSecure(
            @RequestPayload GetFileDetailCivilSecure search) throws JsonProcessingException {

        var inner =
                search.getGetFileDetailCivilSecureRequest() != null
                                && search.getGetFileDetailCivilSecureRequest()
                                                .getGetFileDetailCivilSecureRequest()
                                        != null
                        ? search.getGetFileDetailCivilSecureRequest()
                                .getGetFileDetailCivilSecureRequest()
                        : new ca.bc.gov.open.pcss.secure.one.GetFileDetailCivilSecureRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "secure/file-detail")
                        .queryParam("requestAgenId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", InstantSerializer.convert(inner.getRequestDtm()))
                        .queryParam("applicationCd", inner.getApplicationCd())
                        .queryParam("physicalFileId", inner.getPhysicalFileId());
        try {
            HttpEntity<ca.bc.gov.open.pcss.secure.one.GetFileDetailCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            ca.bc.gov.open.pcss.secure.one.GetFileDetailCivilResponse.class);

            var out = new GetFileDetailCivilSecureResponse();
            var one = new GetFileDetailCivilResponse();
            one.setGetFileDetailCivilResponse(resp.getBody());
            out.setGetFileDetailCivilResponse(one);
            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "getFileDetailCivilSecure")));
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getFileDetailCivilSecure",
                                    ex.getMessage(),
                                    inner)));
            throw new ORDSException();
        }
    }
}
