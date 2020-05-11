package ca.bc.gov.open.pcss.pcsswebservice.civil;

import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilPartyRequest;
import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilPartyResponse2;
import ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyResponse;
import ca.bc.gov.open.pcss.civil.Party;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.PartyData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.PartyResults;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFilePartyData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFilePartyResponse;
import ca.bc.gov.open.pcss.pcsswebservice.Keys;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EndpointGetAppearanceCivilPartyTest {

    public static final String CASE_1 = "CASE_1";
    public static final String CASE_2 = "CASE_2";
    public static final String CANSELNM = "canselnm";
    public static final String COURTPARTICIPANTCCN = "courtparticipantccn";
    public static final BigDecimal COURTPARTICIPANTID = BigDecimal.valueOf(50);
    public static final String GIVENNM = "givennm";
    public static final String LASTNM = "lastnm";
    public static final String ORGNM = "orgnm";
    public static final BigDecimal PARTYID = BigDecimal.valueOf(1);
    public static final String PARTYROLETYPECD = "partyroletypecd";
    public static final String RESPONSE_BODY = "response body";
    private PcssCivilEndpoint sut;

    @Mock
    public PcssApi pcssApiMock;

    @BeforeAll
    public void setUp() throws ApiException {

        MockitoAnnotations.initMocks(this);

        PartyResults test;
        Mockito.when(pcssApiMock.searchFilePartyGet(Mockito.eq(CASE_1))).thenReturn(fakeSearchFilePartyResponse());

        Mockito.when(pcssApiMock.searchFilePartyGet(Mockito.eq(CASE_2))).thenThrow(new ApiException(500, "internal server error", null, RESPONSE_BODY));

        sut = new PcssCivilEndpoint(pcssApiMock);

    }

    @Test
    public void withValidAppearanceIdShouldReturnListOfResults() throws ApiException {

        GetAppearanceCivilPartyRequest request = new GetAppearanceCivilPartyRequest();
        ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyRequest request2 = new ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyRequest();
        request2.setAppearanceId(CASE_1);
        request.setGetAppearanceCivilPartyRequest(request2);

        GetAppearanceCivilPartyResponse2 appearanceCivilPartyResponse2 = sut.getAppearanceCivilParty(request);

        GetAppearanceCivilPartyResponse civilPartyResponse = appearanceCivilPartyResponse2.getGetAppearanceCivilPartyResponse();

        Assertions.assertEquals("0", civilPartyResponse.getResponseCd());
        Assertions.assertEquals("success", civilPartyResponse.getResponseMessageTxt());

        Assertions.assertEquals(1, civilPartyResponse.getParty().size());

        Party actual = civilPartyResponse.getParty().stream().findFirst().get();

        Assertions.assertEquals(CANSELNM, actual.getCounselNm());
        Assertions.assertEquals(COURTPARTICIPANTCCN, actual.getCourtParticipantCcn());
        Assertions.assertEquals(COURTPARTICIPANTID.toString(), actual.getCourtParticipantId());
        Assertions.assertEquals(GIVENNM, actual.getGivenNm());
        Assertions.assertEquals(LASTNM, actual.getLastNm());
        Assertions.assertEquals(ORGNM, actual.getOrgNm());
        Assertions.assertEquals(PARTYID.toString(), actual.getPartyId());
        Assertions.assertEquals(PARTYROLETYPECD, actual.getPartyRoleTypeCd());

    }

    @Test
    public void withApiExceptionShouldErrorCode() throws ApiException {

        GetAppearanceCivilPartyRequest request = new GetAppearanceCivilPartyRequest();
        ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyRequest request2 = new ca.bc.gov.open.pcss.civil.GetAppearanceCivilPartyRequest();
        request2.setAppearanceId(CASE_2);
        request.setGetAppearanceCivilPartyRequest(request2);

        GetAppearanceCivilPartyResponse2 appearanceCivilPartyResponse2 = sut.getAppearanceCivilParty(request);

        GetAppearanceCivilPartyResponse civilPartyResponse = appearanceCivilPartyResponse2.getGetAppearanceCivilPartyResponse();

        Assertions.assertEquals(Keys.DEFAULT_ERROR_RESPONSE_CD, civilPartyResponse.getResponseCd());
        Assertions.assertEquals(RESPONSE_BODY, civilPartyResponse.getResponseMessageTxt());

    }

    private SearchFilePartyResponse fakeSearchFilePartyResponse() {

        SearchFilePartyResponse result = new SearchFilePartyResponse();
        result.setResponseCd(BigDecimal.valueOf(0));
        result.setResponseMsg("success");

        SearchFilePartyData dataItem = new SearchFilePartyData();
        dataItem.setCounselnm(CANSELNM);
        dataItem.setCourtparticipantccn(COURTPARTICIPANTCCN);
        dataItem.setCourtparticipantid(COURTPARTICIPANTID);
        dataItem.setGivennm(GIVENNM);
        dataItem.setLastnm(LASTNM);
        dataItem.setOrgnm(ORGNM);
        dataItem.setPartyid(PARTYID);
        dataItem.setPartyroletypecd(PARTYROLETYPECD);

        result.addDataItem(dataItem);

        return result;

    }

}
