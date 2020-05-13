package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;


import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.civil.Resource;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResourcesData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { ObjectFactory.class } )
public interface ResourceMapper {

    @Mapping(target = "bookingId", source = "bookingid")
    @Mapping(target = "bookingSeqNo", source = "bookingseqno")
    @Mapping(target = "assetTypeCd", source = "assettypecd")
    @Mapping(target = "assetUsageRuleCd", source = "assetusagerulecd")
    @Mapping(target = "bookedForRoleCd", source = "bookedforrolecd")
    @Mapping(target = "courtAgencyId", source = "courtagencyid")
    @Mapping(target = "courtRoomCd", source = "courtroomcd")
    @Mapping(target = "resourceNm", source = "resourcenm")
    @Mapping(target = "resourceId", source = "resourceid")
    @Mapping(target = "bookedDt", source = "bookedfordt")
    @Mapping(target = "bookedFromTm", source = "bookedfromtm")
    @Mapping(target = "bookedToTm", source = "bookedtotm")
    @Mapping(target = "bookedByNm", source = "bookedbynm")
    @Mapping(target = "bookingCommentTxt", source = "bookingcommenttxt")
    @Mapping(target = "bookingCcn", source = "bookingccn")
    Resource toResource(SearchFileAppearanceResourcesData searchFileAppearanceResourcesData);
    
    
}
