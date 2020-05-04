package ca.bc.gov.open.pcss.pcsscommonapi;


import ca.bc.gov.courts.xml.ns.pcss.common.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PcssCommonEndpoint implements PcssCommonPortType {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public SetCourtCalendarResponse2 setCourtCalendar(SetCourtCalendarRequest setCourtCalendarRequest) {
        return null;
    }

    @Override
    public SetAppearanceMoveResponse2 setAppearanceMove(SetAppearanceMoveRequest setAppearanceMoveRequest) {
        return null;
    }

    @Override
    public GetCourtCalendarDetailByDayResponse2 getCourtCalendarDetailByDay(GetCourtCalendarDetailByDayRequest getCourtCalendarDetailByDayRequest) {
        return null;
    }

    @Override
    public GetOperationReportResponse2 getOperationReport(GetOperationReportRequest getOperationReportRequest) {
        return null;
    }

    @Override
    public GetOperationReportLovResponse2 getOperationReportLov(GetOperationReportLovRequest getOperationReportLovRequest) {
        return null;
    }

    @Override
    public SetSyncCompleteResponse2 setSyncComplete(SetSyncCompleteRequest setSyncCompleteRequest) {
        return null;
    }

    @Override
    public GetFileSearchResponse2 getFileSearch(GetFileSearchRequest getFileSearchRequest) {
        return null;
    }

    @Override
    public GetUserLoginResponse2 getUserLogin(GetUserLoginRequest getUserLoginRequest) {
        return null;
    }

    @Override
    public GetReservedJudgmentResponse2 getReservedJudgment(GetReservedJudgmentRequest getReservedJudgmentRequest) {
        return null;
    }

    @Override
    public GetResourceAvailabilityResponse2 getResourceAvailability(GetResourceAvailabilityRequest getResourceAvailabilityRequest) {
        return null;
    }

    @Override
    public SetAppearanceStatusResponse2 setAppearanceStatus(SetAppearanceStatusRequest setAppearanceStatusRequest) {
        return null;
    }

    @Override
    public SetAppearanceUpdateResponse2 setAppearanceUpdate(SetAppearanceUpdateRequest setAppearanceUpdateRequest) {
        return null;
    }

    @Override
    public GetCodeValuesResponse2 getCodeValues(GetCodeValuesRequest getCodeValuesRequest) {
        return null;
    }

    @Override
    public SetResourceBookingResponse2 setResourceBooking(SetResourceBookingRequest setResourceBookingRequest) {
        return null;
    }

    @Override
    public SetResourceCancelResponse2 setResourceCancel(SetResourceCancelRequest setResourceCancelRequest) {
        return null;
    }
}
