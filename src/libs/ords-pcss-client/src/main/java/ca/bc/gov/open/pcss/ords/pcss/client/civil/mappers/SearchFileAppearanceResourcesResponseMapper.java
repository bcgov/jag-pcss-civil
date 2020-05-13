package ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers;

import ca.bc.gov.open.pcss.ords.pcss.client.Keys;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResourcesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchFileAppearanceResourcesResponseMapper {

    SearchFileAppearanceResourcesResponseMapper INSTANCE = Mappers.getMapper(SearchFileAppearanceResourcesResponseMapper.class);

    @Mapping(target = "responseMsg", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    SearchFileAppearanceResourcesResponse toSearchFileAppearanceResourcesResponse(ApiException e);

}
