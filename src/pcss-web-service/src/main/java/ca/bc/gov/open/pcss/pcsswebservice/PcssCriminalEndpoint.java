package ca.bc.gov.open.pcss.pcsswebservice;

import ca.bc.gov.courts.xml.ns.pcss.criminal.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PcssCriminalEndpoint implements PcssCriminalPortType {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public GetAppearanceCriminalCountResponse2 getAppearanceCriminalCount(GetAppearanceCriminalCountRequest getAppearanceCriminalCountRequest) {
        return null;
    }

    @Override
    public GetPersonnelAvailDetailResponse2 getPersonnelAvailDetail(GetPersonnelAvailDetailRequest getPersonnelAvailDetailRequest) {
        return null;
    }

    @Override
    public GetSyncCriminalHearingRestrictionResponse2 getSyncCriminalHearingRestriction(GetSyncCriminalHearingRestrictionRequest getSyncCriminalHearingRestrictionRequest) {
        return null;
    }

    @Override
    public GetCrownAssignmentResponse2 getCrownAssignment(GetCrownAssignmentRequest getCrownAssignmentRequest) {
        return null;
    }

    @Override
    public SetCrownFileDetailResponse2 setCrownFileDetail(SetCrownFileDetailRequest setCrownFileDetailRequest) {
        return null;
    }

    @Override
    public SetAppearanceMethodCriminalResponse2 setAppearanceMethodCriminal(SetAppearanceMethodCriminalRequest setAppearanceMethodCriminalRequest) {
        return null;
    }

    @Override
    public SetFileNoteResponse2 setFileNote(SetFileNoteRequest setFileNoteRequest) {
        return null;
    }

    @Override
    public GetClosedFileResponce getClosedFile(GetClosedFileRequest getClosedFileRequest) {
        return null;
    }

    @Override
    public GetPersonnelSearchResponse2 getPersonnelSearch(GetPersonnelSearchRequest getPersonnelSearchRequest) {
        return null;
    }

    @Override
    public GetAppearanceCriminalResourceResponse2 getAppearanceCriminalResource(GetAppearanceCriminalResourceRequest getAppearanceCriminalResourceRequest) {
        return null;
    }

    @Override
    public GetPersonnelAvailabilityResponse2 getPersonnelAvailability(GetPersonnelAvailabilityRequest getPersonnelAvailabilityRequest) {
        return null;
    }

    @Override
    public GetFileDetailCriminalResponse2 getFileDetailCriminal(GetFileDetailCriminalRequest getFileDetailCriminalRequest) {
        return null;
    }

    @Override
    public SetAppearanceCriminalResponse2 setAppearanceCriminal(SetAppearanceCriminalRequest setAppearanceCriminalRequest) {
        return null;
    }

    @Override
    public GetAppearanceCriminalResponse2 getAppearanceCriminal(GetAppearanceCriminalRequest getAppearanceCriminalRequest) {
        return null;
    }

    @Override
    public SetCounselDetailCriminalResponse2 setCounselDetailCriminal(SetCounselDetailCriminalRequest setCounselDetailCriminalRequest) {
        return null;
    }

    @Override
    public GetAppearanceCriminalApprMethodResponse2 getAppearanceCriminalApprMethod(GetAppearanceCriminalApprMethodRequest getAppearanceCriminalApprMethodRequest) {
        return null;
    }

    @Override
    public GetSyncCriminalAppearanceResponse2 getSyncCriminalAppearance(GetSyncCriminalAppearanceRequest getSyncCriminalAppearanceRequest) {
        return null;
    }

    @Override
    public SetHearingRestrictionCriminalResponse2 setHearingRestrictionCriminal(SetHearingRestrictionCriminalRequest setHearingRestrictionCriminalRequest) {
        return null;
    }

    @Override
    public SetCrownAssignmentResponse2 setCrownAssignment(SetCrownAssignmentRequest setCrownAssignmentRequest) {
        return null;
    }
}
