package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.civil.Party;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFilePartyData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { ObjectFactory.class } )
public interface PartyMapper {

    @Mapping(source = "partyid", target = "partyId")
    @Mapping(source = "partyroletypecd", target = "partyRoleTypeCd")
    @Mapping(source = "lastnm", target = "lastNm")
    @Mapping(source = "givennm", target = "givenNm")
    @Mapping(source = "orgnm", target = "orgNm")
    @Mapping(source = "counselnm", target = "counselNm")
    @Mapping(source = "courtparticipantid", target = "courtParticipantId")
    @Mapping(source = "courtparticipantccn", target = "courtParticipantCcn")
    Party toSearchFilePartyData(SearchFilePartyData searchFilePartyData);

}
