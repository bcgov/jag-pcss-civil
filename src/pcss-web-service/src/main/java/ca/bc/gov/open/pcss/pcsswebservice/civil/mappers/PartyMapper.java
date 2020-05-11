package ca.bc.gov.open.pcss.pcsswebservice.civil.mappers;

import ca.bc.gov.open.pcss.civil.ObjectFactory;
import ca.bc.gov.open.pcss.civil.Party;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFilePartyData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses= { ObjectFactory.class } )
public interface PartyMapper {

    @Mapping(target = "partyId", source = "partyid")
    @Mapping( target = "partyRoleTypeCd",source = "partyroletypecd")
    @Mapping(target = "lastNm", source = "lastnm")
    @Mapping(target = "givenNm", source = "givennm")
    @Mapping(target = "orgNm", source = "orgnm")
    @Mapping(target = "counselNm", source = "counselnm")
    @Mapping(target = "courtParticipantId", source = "courtparticipantid")
    @Mapping(target = "courtParticipantCcn", source = "courtparticipantccn")
    Party toSearchFilePartyData(SearchFilePartyData searchFilePartyData);

}
