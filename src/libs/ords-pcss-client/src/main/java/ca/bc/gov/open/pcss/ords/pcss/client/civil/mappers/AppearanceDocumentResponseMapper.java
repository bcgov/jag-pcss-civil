package ca.bc.gov.open.pcss.ords.pcss.client.civil.mappers;

import ca.bc.gov.open.pcss.ords.pcss.client.Keys;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceDocumentResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppearanceDocumentResponseMapper {

    AppearanceDocumentResponseMapper INSTANCE = Mappers.getMapper(AppearanceDocumentResponseMapper.class);

    AppearanceDocumentResponse toAppearanceDocumentResponse(SearchFileAppearanceDocumentResponse searchFileAppearanceDocumentResponse);

    @Mapping(target = "responseMsg", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    AppearanceDocumentResponse toAppearanceDocumentResponse(ApiException apiException);

}
