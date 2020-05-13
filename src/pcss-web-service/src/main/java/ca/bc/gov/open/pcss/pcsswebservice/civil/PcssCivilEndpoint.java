package ca.bc.gov.open.pcss.pcsswebservice.civil;


import ca.bc.gov.courts.xml.ns.pcss.civil.v1.*;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.CivilService;
import ca.bc.gov.open.pcss.pcsswebservice.Keys;
import ca.bc.gov.open.pcss.pcsswebservice.civil.mappers.AppearanceCivilApprMethodResponseMapper;
import ca.bc.gov.open.pcss.pcsswebservice.civil.mappers.AppearanceCivilDocumentResponseMapper;
import ca.bc.gov.open.pcss.pcsswebservice.civil.mappers.AppearanceCivilPartyMapper;
import ca.bc.gov.open.pcss.pcsswebservice.civil.mappers.AppearanceCivilResponseMapper;
import org.apache.commons.lang3.StringUtils;
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

    private final PcssCivilApi pcssCivilApi;
    private final CivilService civilService;

    public PcssCivilEndpoint(PcssCivilApi pcssCivilApi, CivilService civilService) {
        this.pcssCivilApi = pcssCivilApi;
        this.civilService = civilService;
    }

    @Override
    public GetAppearanceCivilResponse2 getAppearanceCivil(GetAppearanceCivilRequest getAppearanceCivilRequest) {

        logger.debug("received new getAppearanceCivil");

        GetAppearanceCivilResponse2 response2 = new GetAppearanceCivilResponse2();

        try {

            if (getAppearanceCivilRequest.getGetAppearanceCivilRequest() != null) {
                setMDC("getAppearanceCivil",
                        getAppearanceCivilRequest.getGetAppearanceCivilRequest().getRequestAgencyIdentifierId(),
                        getAppearanceCivilRequest.getGetAppearanceCivilRequest().getRequestPartId());
            }

            logger.debug("attempting to call ords api");

            response2
                    .setGetAppearanceCivilResponse(
                            AppearanceCivilResponseMapper
                                    .INSTANCE.toGetAppearanceCivilResponse(
                                    this.pcssCivilApi.civilSearchFileAppearanceGet(
                                            getAppearanceCivilRequest.getGetAppearanceCivilRequest().getFutureYN().value(),
                                            getAppearanceCivilRequest.getGetAppearanceCivilRequest().getHistoryYN().value(),
                                            getAppearanceCivilRequest.getGetAppearanceCivilRequest().getPhysicalFileId())));

            if (response2.getGetAppearanceCivilResponse() != null
                    && isSuccessResponse(response2.getGetAppearanceCivilResponse().getResponseCd())) {
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

        } catch (ApiException e) {

            response2.setGetAppearanceCivilResponse(AppearanceCivilResponseMapper.INSTANCE.toGetAppearanceCivilResponse(e));
            logger.error("ORDS Api exception while calling getAppearanceCivilParty", e);

        } finally {
            cleanUpMDC();
        }

        return response2;
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
        logger.debug("received new getAppearanceCivilDocument");

        GetAppearanceCivilDocumentResponse2 response2 = new GetAppearanceCivilDocumentResponse2();


        if (getAppearanceCivilDocumentRequest.getGetAppearanceCivilDocumentRequest() != null) {
            setMDC("getAppearanceCivilDocument",
                    getAppearanceCivilDocumentRequest.getGetAppearanceCivilDocumentRequest().getRequestAgencyIdentifierId(),
                    getAppearanceCivilDocumentRequest.getGetAppearanceCivilDocumentRequest().getRequestPartId());
        }

        logger.debug("attempting to call ords api");

        response2
                .setGetAppearanceCivilDocumentResponse(
                        AppearanceCivilDocumentResponseMapper
                                .INSTANCE.toGetAppearanceCivilDocumentResponse(
                                this.civilService.getCivilSearchFileAppearanceDocument(getAppearanceCivilDocumentRequest.getGetAppearanceCivilDocumentRequest().getAppearanceId())));

        if (response2.getGetAppearanceCivilDocumentResponse() != null
                && isSuccessResponse(response2.getGetAppearanceCivilDocumentResponse().getResponseCd())) {
            logger.info("successfully retrieved getAppearanceCivilDocument [{}]",
                    getAppearanceCivilDocumentRequest.getGetAppearanceCivilDocumentRequest().getAppearanceId());
        } else {
            logger.error("error retrieving getAppearanceCivilDocument [{}], error: [{}]",
                    response2.getGetAppearanceCivilDocumentResponse().getResponseCd(),
                    response2.getGetAppearanceCivilDocumentResponse().getResponseMessageTxt());
        }


        cleanUpMDC();

        return response2;
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

        logger.debug("received new getAppearanceCivilApprMethod");

        GetAppearanceCivilApprMethodResponse2 response2 = new GetAppearanceCivilApprMethodResponse2();

        try {

            if (getAppearanceCivilApprMethodRequest.getGetAppearanceCivilApprMethodRequest() != null) {
                setMDC("getAppearanceCivilApprMethod",
                        getAppearanceCivilApprMethodRequest.getGetAppearanceCivilApprMethodRequest().getRequestAgencyIdentifierId(),
                        getAppearanceCivilApprMethodRequest.getGetAppearanceCivilApprMethodRequest().getRequestPartId());
            }

            logger.debug("attempting to call ords api");
            response2
                    .setGetAppearanceCivilApprMethodResponse(
                            AppearanceCivilApprMethodResponseMapper
                                    .INSTANCE.toGetAppearanceCivilApprMethodResponse(
                                    this.pcssCivilApi.civilSearchFileAppearanceMethodGet(
                                            getAppearanceCivilApprMethodRequest.getGetAppearanceCivilApprMethodRequest().getAppearanceId())));

            if (response2.getGetAppearanceCivilApprMethodResponse() != null
                    && isSuccessResponse(response2.getGetAppearanceCivilApprMethodResponse().getResponseCd())) {
                logger.info("successfully retrieved from ORDS");
            } else {
                logger.error("error retrieving civilSearchFileAppearanceMethod  [{}]",
                        getAppearanceCivilApprMethodRequest.getGetAppearanceCivilApprMethodRequest() == null ?
                                UNKNOWN :
                                getAppearanceCivilApprMethodRequest.getGetAppearanceCivilApprMethodRequest().getAppearanceId());
            }

        } catch (ApiException e) {

            response2.setGetAppearanceCivilApprMethodResponse(AppearanceCivilApprMethodResponseMapper.INSTANCE.toGetAppearanceCivilApprMethodResponse(e));
            logger.error("ORDS Api exception while calling getAppearanceCivilParty", e);

        } finally {
            cleanUpMDC();
        }


        return response2;

    }

    @Override
    public GetAppearanceCivilPartyResponse2 getAppearanceCivilParty(GetAppearanceCivilPartyRequest getAppearanceCivilPartyRequest) {

        logger.debug("received new getAppearanceCivilParty");

        GetAppearanceCivilPartyResponse2 response2 = new GetAppearanceCivilPartyResponse2();

        try {

            if (getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest() != null) {
                setMDC("getAppearanceCivilParty",
                        getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getRequestAgencyIdentifierId(),
                        getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getRequestPartId());
            }

            logger.debug("attempting to call ords api");

            response2
                    .setGetAppearanceCivilPartyResponse(
                            AppearanceCivilPartyMapper
                                    .INSTANCE.toGetAppearanceCivilPartyResponse(
                                    this.pcssCivilApi.civilSearchFilePartyGet(getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getAppearanceId())));

            if (isSuccessResponse(response2.getGetAppearanceCivilPartyResponse().getResponseCd()))
                logger.info("successfully retrieved getAppearanceCivilParty [{}]",
                        getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getAppearanceId());
            else
                logger.error("error retrieving getAppearanceCivilParty [{}], error: [{}] - {}",
                        getAppearanceCivilPartyRequest.getGetAppearanceCivilPartyRequest().getAppearanceId(),
                        response2.getGetAppearanceCivilPartyResponse() == null ? UNKNOWN :
                                response2.getGetAppearanceCivilPartyResponse().getResponseCd(),
                        response2.getGetAppearanceCivilPartyResponse() == null ? UNKNOWN :
                                response2.getGetAppearanceCivilPartyResponse().getResponseMessageTxt());

        } catch (ApiException e) {

            response2 = new GetAppearanceCivilPartyResponse2();
            response2.setGetAppearanceCivilPartyResponse(AppearanceCivilPartyMapper.INSTANCE.toGetAppearanceCivilPartyResponse(e));
            logger.error("ORDS Api exception while calling getAppearanceCivilParty", e);

        } finally {
            cleanUpMDC();
        }

        return response2;

    }

    private void setMDC(String operation, String requestAgencyIdentifierId, String requestPartId) {
        MDC.put(OPERATION, operation);
        MDC.put(AGENCY_IDENTIFIER_ID,
                requestAgencyIdentifierId);
        MDC.put(PART_ID, requestPartId);
    }

    private void cleanUpMDC() {
        MDC.remove(OPERATION);
        MDC.remove(AGENCY_IDENTIFIER_ID);
        MDC.remove(PART_ID);
    }

    private boolean isSuccessResponse(String responseCd) {
        return StringUtils.equals(Keys.DEFAULT_SUCCESS_RESPONSE_CD, responseCd);
    }
}