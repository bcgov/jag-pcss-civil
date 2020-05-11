package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyResponse;
import ca.bc.gov.open.pcss.civil.GetAppearanceCivilResponse;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResponse;
import ca.bc.gov.open.pcss.pcsswebservice.Keys;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= { ObjectFactory.class, ApprDetailMapper.class } )
public interface AppearanceCivilResponseMapper {

    AppearanceCivilResponseMapper INSTANCE = Mappers.getMapper( AppearanceCivilResponseMapper.class );

    @Mapping(target = "responseMessageTxt", source = "responseMsg")
    @Mapping(target = "futureRecCount", source = "futureRecordCnt")
    @Mapping(target = "historyRecCount", source = "historyRecordCnt")
    @Mapping(target = "apprDetail", source = "data")
    GetAppearanceCivilResponse toGetAppearanceCivilResponse(SearchFileAppearanceResponse response);

    @Mapping(target = "responseMessageTxt", source = "responseBody")
    @Mapping(target = "responseCd", constant = Keys.DEFAULT_ERROR_RESPONSE_CD)
    GetAppearanceCivilResponse toGetAppearanceCivilResponse(ApiException apiException);

}
