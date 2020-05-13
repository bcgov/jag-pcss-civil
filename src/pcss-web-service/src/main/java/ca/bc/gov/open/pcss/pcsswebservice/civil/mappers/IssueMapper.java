package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.Issue;
import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceIssueData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { ObjectFactory.class } )
public interface IssueMapper {

    @Mapping(target = "issueNumber", source = "issuenumber")
    @Mapping(target = "issueDsc", source = "issuedsc")
    @Mapping(target = "issueResultCd", source = "issueresultcd")
    Issue toIssue(SearchFileAppearanceIssueData searchFileAppearanceIssueData);

}
