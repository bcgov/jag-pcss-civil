package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyResponse;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFilePartyResponse;
import ca.bc.gov.open.pcss.pcsswebservice.Keys;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= { ObjectFactory.class, PartyMapper.class } )
public interface AppearanceCivilPartyMapper {


    AppearanceCivilPartyMapper INSTANCE = Mappers.getMapper( AppearanceCivilPartyMapper.class );

    @Mapping(target = "responseMessageTxt", source = "responseMsg")
    @Mapping(target = "party", source = "data")
    GetAppearanceCivilPartyResponse toGetAppearanceCivilPartyResponse(SearchFilePartyResponse getAppearanceCivilPartyResponse);

    @Mapping(target = "responseMessageTxt", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    GetAppearanceCivilPartyResponse toGetAppearanceCivilPartyResponse(ApiException apiException);


}
