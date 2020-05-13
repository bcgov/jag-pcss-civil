package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.Document;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { ObjectFactory.class, IssueMapper.class } )
public interface DocumentMapper {

    @Mapping(target = "documentId", source = "documentid")
    @Mapping(target = "fileSeqNo", source = "filesegno")
    @Mapping(target = "documentTypeCd", source = "documenttypecd")
    @Mapping(target = "appearanceReasonCd", source = "appearancereasoncd")
    @Mapping(target = "appearanceResultCd", source = "appearancerusltcd")
    @Mapping(target = "filedDt", source = "fileddt")
    @Mapping(target = "documentHearingCcn", source = "documenthearingccn")
    @Mapping(target = "issue", source = "searchFileAppearanceIssueDataList")
    Document toDocument(AppearanceDocumentData appearanceDocumentData);

}
