package ca.bc.gov.open.Pcss.Controllers;

import ca.bc.gov.open.Pcss.Configuration.SoapConfig;
import com.example.demp.wsdl.pcss.three.*;
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
public class SyncController {
    @Value("${pcss.host}")
    private String host = "https://127.0.0.1/";

    private final RestTemplate restTemplate;

    @Autowired
    public SyncController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getSyncCivilAppearance")
    @ResponsePayload
    public GetSyncCivilAppearanceResponse getSyncCivilAppearance(
            @RequestPayload GetSyncCivilAppearance search) {

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
    }

    @PayloadRoot(
            namespace = SoapConfig.SOAP_NAMESPACE,
            localPart = "getSyncCivilHearingRestriction")
    @ResponsePayload
    public GetSyncCivilHearingRestrictionResponse getSyncCivilHearingRestriction(
            @RequestPayload GetSyncCivilHearingRestriction search) {

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
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "setHearingRestrictionCivil")
    @ResponsePayload
    public SetHearingRestrictionCivilResponse setSyncCivilHearingRestriction(
            @RequestPayload SetHearingRestrictionCivil search) {

        var inner =
                search.getSetHearingRestrictionCivilRequest() != null
                                && search.getSetHearingRestrictionCivilRequest()
                                                .getSetHearingRestrictionCivilRequest()
                                        != null
                        ? search.getSetHearingRestrictionCivilRequest()
                                .getSetHearingRestrictionCivilRequest()
                        : new com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "SetHearingRestrictionCivil");

        HttpEntity<com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilRequest> body =
                new HttpEntity<>(inner, new HttpHeaders());

        HttpEntity<com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilResponse> resp =
                restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.POST,
                        body,
                        com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilResponse.class);

        var out = new SetHearingRestrictionCivilResponse();
        var one = new SetHearingRestrictionCivilResponse2();
        one.setSetHearingRestrictionCivilResponse(resp.getBody());
        out.setSetHearingRestrictionCivilResponse(one);
        return out;
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "GetFileDetailCivil")
    @ResponsePayload
    public GetFileDetailCivilResponse getFileDetailCivil(
            @RequestPayload GetFileDetailCivil search) {

        var inner =
                search.getGetFileDetailCivilRequest() != null
                                && search.getGetFileDetailCivilRequest()
                                                .getGetFileDetailCivilRequest()
                                        != null
                        ? search.getGetFileDetailCivilRequest().getGetFileDetailCivilRequest()
                        : new com.example.demp.wsdl.pcss.one.GetFileDetailCivilRequest();

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(host + "GetFileDetailCivil")
                        .queryParam(
                                "RequestAgencyIdentifierId", inner.getRequestAgencyIdentifierId())
                        .queryParam("RequestPartId", inner.getRequestPartId())
                        .queryParam("RequestDtm", inner.getRequestDtm())
                        .queryParam("PhysicalFileId", inner.getPhysicalFileId());

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
    }
}
