package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.GetAppearanceCivilApprMethodResponse;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceMethodResponse;
import ca.bc.gov.open.pcss.pcsswebservice.Keys;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= { ObjectFactory.class, AppearanceMethodMapper.class } )
public interface AppearanceCivilApprMethodResponseMapper {

    AppearanceCivilApprMethodResponseMapper INSTANCE = Mappers.getMapper(AppearanceCivilApprMethodResponseMapper.class );

    @Mapping(target = "responseMessageTxt", source = "responseMsg")
    @Mapping(target = "appearanceMethod", source = "data")
    GetAppearanceCivilApprMethodResponse toGetAppearanceCivilApprMethodResponse(SearchFileAppearanceMethodResponse response);

    @Mapping(target = "responseMessageTxt", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    GetAppearanceCivilApprMethodResponse toGetAppearanceCivilApprMethodResponse(ApiException e);
}
