package ca.bc.gov.open.pcss.pcsswebservice;


import ca.bc.gov.courts.xml.ns.pcss.civil.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PcssCivilEndpoint implements PcssCivilPortType {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        return null;
    }
}
