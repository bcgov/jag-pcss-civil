package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.GetAppearanceCivilResourceResponse;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResourcesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= { ObjectFactory.class, ResourceMapper.class } )
public interface AppearanceCivilResourceResponseMapper {

    AppearanceCivilResourceResponseMapper INSTANCE = Mappers.getMapper( AppearanceCivilResourceResponseMapper.class );

    @Mapping(target = "responseMessageTxt", source = "responseMsg")
    @Mapping(target = "resource", source = "data")
    GetAppearanceCivilResourceResponse toGetAppearanceCivilResourceResponse2(SearchFileAppearanceResourcesResponse searchFileAppearanceResourcesResponse);

}
