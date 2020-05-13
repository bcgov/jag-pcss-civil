package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.GetAppearanceCivilDocumentResponse;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= { ObjectFactory.class, DocumentMapper.class} )
public interface AppearanceCivilDocumentResponseMapper {


    AppearanceCivilDocumentResponseMapper INSTANCE = Mappers.getMapper(AppearanceCivilDocumentResponseMapper.class);

    @Mapping(target = "responseMessageTxt", source = "responseMsg")
    @Mapping(target = "document", source = "data")
    GetAppearanceCivilDocumentResponse toGetAppearanceCivilDocumentResponse(AppearanceDocumentResponse searchFileAppearanceDocumentResponse);

}
