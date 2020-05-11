package ca.bc.gov.open.pcss.pcsswebservice.civil;


import ca.bc.gov.courts.xml.ns.pcss.civil.v1.*;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.pcsswebservice.Keys;
import ca.bc.gov.open.pcss.pcsswebservice.civil.mappers.AppearanceCivilPartyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PcssCivilEndpoint implements PcssCivilPortType {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PcssApi pcssApi;

    public PcssCivilEndpoint(PcssApi pcssApi) {
        this.pcssApi = pcssApi;
    }

    @Override
    public GetAppearanceCivilResponse2 getAppearanceCivil(GetAppearanceCivilRequest getAppearanceCivilRequest) {
        return null;
    }

    @Override
    public SetAppearanceMethodCivilResponse2 setAppearanceMethodCivil(SetAppearanceMethodCivilRequest setAppearanceMethodCivilRequest) {
        return null;
    }

    @Override
    public GetFileDetailCivilResponse2 getFileDetailCivil(GetFileDetailCivilRequest getFileDetailCivilRequest) {
        return null;
    }

    @Override
    public GetSyncCivilAppearanceResponse2 getSyncCivilAppearance(GetSyncCivilAppearanceRequest getSyncCivilAppearanceRequest) {
        return null;
    }

    @Override
    public SetCounselDetailCivilResponse2 setCounselDetailCivil(SetCounselDetailCivilRequest setCounselDetailCivilRequest) {
        return null;
    }

    @Override
    public GetAppearanceCivilResourceResponse2 getAppearanceCivilResource(GetAppearanceCivilResourceRequest getAppearanceCivilResourceRequest) {
        return null;
    }

    @Override
    public SetHearingRestrictionCivilResponse2 setHearingRestrictionCivil(SetHearingRestrictionCivilRequest setHearingRestrictionCivilRequest) {
        return null;
    }

    @Override
    public GetAppearanceCivilDocumentResponse2 getAppearanceCivilDocument(GetAppearanceCivilDocumentRequest getAppearanceCivilDocumentRequest) {
        return null;
    }

    @Override
    public SetAppearanceCivilResponse2 setAppearanceCivil(SetAppearanceCivilRequest setAppearanceCivilRequest) {
        return null;
    }

    @Override
    public GetSyncCivilHearingRestrictionResponse2 getSyncCivilHearingRestriction(GetSyncCivilHearingRestrictionRequest getSyncCivilHearingRestrictionRequest) {
        return null;
    }

    @Override
    public GetAppearanceCivilApprMethodResponse2 getAppearanceCivilApprMethod(GetAppearanceCivilApprMethodRequest getAppearanceCivilApprMethodRequest) {
        return null;
    }

    @Override
    public GetAppearanceCivilPartyResponse2 getAppearanceCivilParty(GetAppearanceCivilPartyRequest getAppearanceCivilPartyRequest) {


        try {

            GetAppearanceCivilPartyResponse2 response2 = new GetAppearanceCivilPartyResponse2();

            response2
                    .setGetAppearanceCivilPartyResponse(
                            AppearanceCivilPartyMapper
                                    .INSTANCE.toGetAppearanceCivilPartyResponse(
                                    this.pcssApi.searchFilePartyGet(getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getAppearanceId())));

            return response2;


        } catch (ApiException e) {
            GetAppearanceCivilPartyResponse2 response2 = new GetAppearanceCivilPartyResponse2();

            ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyResponse response = new ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyResponse();

            response.setResponseCd(Keys.DEFAULT_ERROR_RESPONSE_CD);
            response.setResponseMessageTxt(e.getResponseBody());
            response2.setGetAppearanceCivilPartyResponse(response);

            return response2;
        }

    }
}