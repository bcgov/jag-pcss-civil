package ca.bc.gov.open.Pcss.Controllers;

import ca.bc.gov.open.Pcss.Configuration.SoapConfig;
import ca.bc.gov.open.Pcss.Exceptions.ORDSException;
import ca.bc.gov.open.Pcss.Models.OrdsErrorLog;
import com.example.demp.wsdl.pcss.secure.two.*;
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
    public GetAppearanceCivilApprMethodSecureResponse getGetAppearanceCivilApprMethodSecureRequest(
            @RequestPayload GetAppearanceCivilApprMethodSecureRequest search)
            throws JsonProcessingException {

        var inner =
                search.getGetAppearanceCivilApprMethodSecureRequest() != null
                        ? search.getGetAppearanceCivilApprMethodSecureRequest()
                        : new com.example.demp.wsdl.pcss.secure.one
                                .GetAppearanceCivilApprMethodSecureRequest();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "health")
                        .queryParam(
                                "requestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("applicationCd", inner.getApplicationCd())
                        .queryParam("appearanceId", inner.getRequestPartId());
        try {
            HttpEntity<com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilApprMethodResponse>
                    resp =
                            restTemplate.exchange(
                                    builder.toUriString(),
                                    HttpMethod.GET,
                                    new HttpEntity<>(new HttpHeaders()),
                                    com.example.demp.wsdl.pcss.secure.one
                                            .GetAppearanceCivilApprMethodResponse.class);

            var out = new GetAppearanceCivilApprMethodSecureResponse();
            var one = new GetAppearanceCivilApprMethodResponse();
            one.setGetAppearanceCivilApprMethodResponse(resp.getBody());
            out.setGetAppearanceCivilApprMethodResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilApprMethodSecure",
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getAppearanceCivilPartySecure")
    @ResponsePayload
    public GetAppearanceCivilPartySecureResponse getAppearanceCivilPartySecure(
            @RequestPayload GetAppearanceCivilPartySecureRequest search)
            throws JsonProcessingException {
        var inner =
                search.getGetAppearanceCivilPartySecureRequest() != null
                        ? search.getGetAppearanceCivilPartySecureRequest()
                        : new com.example.demp.wsdl.pcss.secure.one
                                .GetAppearanceCivilPartySecureRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "health")
                        .queryParam(
                                "requestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("applicationCd", inner.getApplicationCd())
                        .queryParam("appearanceId", inner.getRequestPartId());
        try {
            HttpEntity<com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilPartyResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilPartyResponse
                                    .class);

            var out = new GetAppearanceCivilPartySecureResponse();
            var one = new GetAppearanceCivilPartyResponse();
            one.setGetAppearanceCivilPartyResponse(resp.getBody());
            out.setGetAppearanceCivilPartyResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilPartySecure",
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
                        : new com.example.demp.wsdl.pcss.secure.one
                                .GetAppearanceCivilSecureRequest();
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "health")
                        .queryParam(
                                "requestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("applicationCd", inner.getApplicationCd())
                        .queryParam("appearanceId", inner.getRequestPartId());
        try {
            HttpEntity<com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilResponse.class);

            var out = new GetAppearanceCivilSecureResponse();
            var one = new GetAppearanceCivilResponse();
            one.setGetAppearanceCivilResponse(resp.getBody());
            out.setGetAppearanceCivilResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getAppearanceCivilSecure",
                                    inner)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getFileDetailCivilSecure")
    @ResponsePayload
    public GetFileDetailCivilSecureResponse getFileDetailCivilSecure(
            @RequestPayload GetFileDetailCivilSecureRequest search) throws JsonProcessingException {

        var inner =
                search.getGetFileDetailCivilSecureRequest() != null
                        ? search.getGetFileDetailCivilSecureRequest()
                        : new com.example.demp.wsdl.pcss.secure.one
                                .GetFileDetailCivilSecureRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "health")
                        .queryParam(
                                "requestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("requestPartId", inner.getRequestPartId())
                        .queryParam("requestDtm", inner.getRequestDtm())
                        .queryParam("applicationCd", inner.getApplicationCd())
                        .queryParam("appearanceId", inner.getRequestPartId());
        try {
            HttpEntity<com.example.demp.wsdl.pcss.secure.one.GetFileDetailCivilResponse> resp =
                    restTemplate.exchange(
                            builder.toUriString(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            com.example.demp.wsdl.pcss.secure.one.GetFileDetailCivilResponse.class);

            var out = new GetFileDetailCivilSecureResponse();
            var one = new GetFileDetailCivilResponse();
            one.setGetFileDetailCivilResponse(resp.getBody());
            out.setGetFileDetailCivilResponse(one);
            return out;
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "getFileDetailCivilSecure",
                                    inner)));
            throw new ORDSException();
        }
    }
}
