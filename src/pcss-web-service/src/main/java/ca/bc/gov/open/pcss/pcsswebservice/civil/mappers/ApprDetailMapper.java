package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.ApprDetail;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { ObjectFactory.class } )
public interface ApprDetailMapper {

    @Mapping(target = "historyYN", source = "historyyn")
    @Mapping(target = "appearanceId", source = "appearanceid")
    @Mapping(target = "appearanceDt", source = "appearancedt")
    @Mapping(target = "appearanceTm", source = "appearancetm")
    @Mapping(target = "appearanceReasonCd", source = "appearancereasoncd")
    @Mapping(target = "courtAgencyId", source = "courtagencyid")
    @Mapping(target = "courtRoomCd", source = "courroomcd")
    @Mapping(target = "judgeFullNm", source = "judgefullnm")
    @Mapping(target = "judgeInitials", source = "judgeinitials")
    @Mapping(target = "estimatedTimeHour", source = "estimatedtimehour")
    @Mapping(target = "estimatedTimeMin", source = "estimatedtimemin")
    @Mapping(target = "partOfTrialYN", source = "partoftrialyn")
    @Mapping(target = "appearanceStatusCd", source = "appearancestatuscd")
    @Mapping(target = "appearanceResultCd", source = "appearanceresultcd")
    @Mapping(target = "appearanceCcn", source = "appearanceccn")
    @Mapping(target = "documentTypeCd", source = "documenttypecd")
    @Mapping(target = "documentRecCount", source = "documentreccount")
    @Mapping(target = "supplementalEquipmentTxt", source = "supplementalequipmenttxt")
    @Mapping(target = "securityRestrictionTxt", source = "securityrestrictiontxt")
    @Mapping(target = "outOfTownJudgeTxt", source = "outoftownjudgetxt")
    ApprDetail toApprDetail(SearchFileAppearanceData searchFileAppearanceData);

}
