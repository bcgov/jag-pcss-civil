package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.AppearanceMethod;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceMethodData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { ObjectFactory.class } )
public interface AppearanceMethodMapper {

    @Mapping(target = "roleTypeCd", source = "roletypecd")
    @Mapping(target = "appearanceMethodCd", source = "appearancemethodcd")
    @Mapping(target = "apprMethodCcn", source = "appearancemethodccn")
    AppearanceMethod toAppearanceMethod(SearchFileAppearanceMethodData searchFileAppearanceMethodData);

}
