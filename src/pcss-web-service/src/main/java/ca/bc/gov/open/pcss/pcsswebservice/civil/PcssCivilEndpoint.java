package ca.bc.gov.open.pcss.pcsswebservice.civil;


import ca.bc.gov.courts.xml.ns.pcss.civil.v1.*;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.pcsswebservice.civil.mappers.AppearanceCivilPartyMapper;
import ca.bc.gov.open.pcss.pcsswebservice.civil.mappers.AppearanceCivilResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class PcssCivilEndpoint implements PcssCivilPortType {


    private static final String PART_ID = "partId";
    private static final String AGENCY_IDENTIFIER_ID = "agencyIdentifierId";
    private static final String UNKNOWN = "UNKNOWN";
    private static final String OPERATION = "operation";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PcssApi pcssApi;

    public PcssCivilEndpoint(PcssApi pcssApi) {
        this.pcssApi = pcssApi;
    }

    @Override
    public GetAppearanceCivilResponse2 getAppearanceCivil(GetAppearanceCivilRequest getAppearanceCivilRequest) {
        logger.debug("received new getAppearanceCivil");


        GetAppearanceCivilResponse2 response2 = new GetAppearanceCivilResponse2();

        try {

            if (getAppearanceCivilRequest.getGetAppearanceCivilRequest() != null) {
                MDC.put(OPERATION, "getAppearanceCivil");
                MDC.put(AGENCY_IDENTIFIER_ID,
                        getAppearanceCivilRequest.getGetAppearanceCivilRequest().getRequestAgencyIdentifierId());
                MDC.put(PART_ID, getAppearanceCivilRequest.getGetAppearanceCivilRequest().getRequestPartId());
            }

            logger.debug("attempting to call ords api");
            response2
                    .setGetAppearanceCivilResponse(
                            AppearanceCivilResponseMapper
                                    .INSTANCE.toGetAppearanceCivilResponse(
                                    this.pcssApi.searchFileAppearanceGet(
                                            getAppearanceCivilRequest.getGetAppearanceCivilRequest().getFutureYN().value(),
                                            getAppearanceCivilRequest.getGetAppearanceCivilRequest().getHistoryYN().value(),
                                            getAppearanceCivilRequest.getGetAppearanceCivilRequest().getPhysicalFileId())));

            if (response2.getGetAppearanceCivilResponse() != null
                    && response2.getGetAppearanceCivilResponse().getResponseCd() == "0") {
                logger.info("successfully retrieved getAppearanceCivilParty [{}]",
                        getAppearanceCivilRequest.getGetAppearanceCivilRequest().getPhysicalFileId());
            } else {
                logger.error("error retrieving getAppearanceCivilParty [{}], error: [{}] - {}",
                        getAppearanceCivilRequest.getGetAppearanceCivilRequest().getPhysicalFileId(),
                        response2.getGetAppearanceCivilResponse() == null ? UNKNOWN :
                                response2.getGetAppearanceCivilResponse().getResponseCd(),
                        response2.getGetAppearanceCivilResponse() == null ? UNKNOWN :
                                response2.getGetAppearanceCivilResponse().getResponseMessageTxt());
            }

            return response2;

        } catch (ApiException e) {

            response2.setGetAppearanceCivilResponse(AppearanceCivilResponseMapper.INSTANCE.toGetAppearanceCivilResponse(e));
            logger.error("ORDS Api exception while calling getAppearanceCivilParty", e);
            return response2;

        } finally {

            MDC.remove(OPERATION);
            MDC.remove(AGENCY_IDENTIFIER_ID);
            MDC.remove(PART_ID);

        }
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

        logger.debug("received new getAppearanceCivilParty");

        try {

            if (getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest() != null) {
                MDC.put(OPERATION, "getAppearanceCivilParty");
                MDC.put(AGENCY_IDENTIFIER_ID,
                        getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getRequestAgencyIdentifierId());
                MDC.put(PART_ID, getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getRequestPartId());
            }

            GetAppearanceCivilPartyResponse2 response2 = new GetAppearanceCivilPartyResponse2();

            logger.debug("attempting to call ords api");
            response2
                    .setGetAppearanceCivilPartyResponse(
                            AppearanceCivilPartyMapper
                                    .INSTANCE.toGetAppearanceCivilPartyResponse(
                                    this.pcssApi.searchFilePartyGet(getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getAppearanceId())));

            if (response2.getGetAppearanceCivilPartyResponse().getResponseCd() == "0")
                logger.info("successfully retrieved getAppearanceCivilParty [{}]",
                        getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getAppearanceId());
            else
                logger.error("error retrieving getAppearanceCivilParty [{}], error: [{}] - {}",
                        getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getAppearanceId(),
                        response2.getGetAppearanceCivilPartyResponse() == null ? UNKNOWN :
                                response2.getGetAppearanceCivilPartyResponse().getResponseCd(),
                        response2.getGetAppearanceCivilPartyResponse() == null ? UNKNOWN :
                                response2.getGetAppearanceCivilPartyResponse().getResponseMessageTxt());

            return response2;


        } catch (ApiException e) {

            GetAppearanceCivilPartyResponse2 response2 = new GetAppearanceCivilPartyResponse2();
            response2.setGetAppearanceCivilPartyResponse(AppearanceCivilPartyMapper.INSTANCE.toGetAppearanceCivilPartyResponse(e));
            logger.error("ORDS Api exception while calling getAppearanceCivilParty", e);
            return response2;

        } finally {

            MDC.remove(OPERATION);
            MDC.remove(AGENCY_IDENTIFIER_ID);
            MDC.remove(PART_ID);

        }

    }
}