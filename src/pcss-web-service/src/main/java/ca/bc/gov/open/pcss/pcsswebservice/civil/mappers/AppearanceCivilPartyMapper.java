package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilPartyResponse2;
import ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyResponse;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFilePartyResponse;
import ca.bc.gov.open.pcss.pcsswebservice.civil.mappers.PartyMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= { ObjectFactory.class, PartyMapper.class } )
public interface AppearanceCivilPartyMapper {


    AppearanceCivilPartyMapper INSTANCE = Mappers.getMapper( AppearanceCivilPartyMapper.class );

    @Mapping(source = "responseMsg", target = "responseMessageTxt")
    @Mapping(source = "data", target = "party")
    GetAppearanceCivilPartyResponse toGetAppearanceCivilPartyResponse(SearchFilePartyResponse getAppearanceCivilPartyResponse);


}
