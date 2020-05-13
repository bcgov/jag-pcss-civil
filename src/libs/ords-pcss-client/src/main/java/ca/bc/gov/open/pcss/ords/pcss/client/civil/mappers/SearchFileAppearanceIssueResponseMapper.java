package ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers;

import ca.bc.gov.open.pcss.ords.pcss.client.Keys;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceIssueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchFileAppearanceIssueResponseMapper {


    SearchFileAppearanceIssueResponseMapper INSTANCE = Mappers.getMapper(SearchFileAppearanceIssueResponseMapper.class);

    @Mapping(target = "responseMsg", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    SearchFileAppearanceIssueResponse toSearchFileAppearanceIssueResponse(ApiException e);

}
