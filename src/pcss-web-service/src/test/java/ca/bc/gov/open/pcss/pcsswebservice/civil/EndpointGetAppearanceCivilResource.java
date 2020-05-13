package ca.bc.gov.open.pcss.pcsswebservice.civil;

import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilResourceRequest;
import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilResourceResponse2;
import ca.bc.gov.courts.xmlschema.pcss.common._1_0.AssetUsageRuleType;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResourcesData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResourcesResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.CivilService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EndpointGetAppearanceCivilResource {


    public static final String RESOURCEID = "Resourceid";
    public static final String COURTROOMCD = "Courtroomcd";
    public static final String COURTAGENCYID = "Courtagencyid";
    public static final String BOOKINGSEQNO = "Bookingseqno";
    public static final String BOOKINGID = "Bookingid";
    public static final String BOOKINGCOMMENTTXT = "Bookingcommenttxt";
    public static final String BOOKINGCCN = "Bookingccn";
    public static final String BOOKEDTOTM = "Bookedtotm";
    public static final String BOOKEDFROMTM = "Bookedfromtm";
    public static final String BOOKEDFORROLECD = "Bookedforrolecd";
    public static final String BOOKEDFORDT = "Bookedfordt";
    public static final String BOOKEDBYNM = "Bookedbynm";
    public static final String ASSETUSAGERULECD = "FIX";
    public static final String ASSETTYPECD = "Assettypecd";
    public static final String RESOURCENM = "Resourcenm";
    public PcssCivilEndpoint sut;

    @Mock
    public PcssCivilApi pcssCivilApiMock;

    @Mock
    public CivilService civilServiceMock;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        Mockito
                .when(civilServiceMock.getAppearanceCivilResource(Mockito.eq(TestHelpers.CASE_1)))
                .thenReturn(getFakeAppearanceCivilResource());

        Mockito
                .when(civilServiceMock.getAppearanceCivilResource(Mockito.eq(TestHelpers.CASE_2)))
                .thenReturn(getFakeErrorAppearanceCivilResource());

        sut = new PcssCivilEndpoint(pcssCivilApiMock, civilServiceMock);

    }

    

    @DisplayName(TestHelpers.CASE_1 + ": When the PCSS api returns a single result.")
    @Test
    public void withResultShouldReturnResult() {

        GetAppearanceCivilResourceRequest request = new GetAppearanceCivilResourceRequest();

        ca.bc.gov.open.pcss.civil.GetAppearanceCivilResourceRequest request2 = new ca.bc.gov.open.pcss.civil.GetAppearanceCivilResourceRequest();

        request2.setAppearanceId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);
        request2.setRequestAgencyIdentifierId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);

        request2.setAppearanceId(TestHelpers.CASE_1);

        request.setGetAppearanceCivilResourceRequest(request2);

        GetAppearanceCivilResourceResponse2 actual =  sut.getAppearanceCivilResource(request);

        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_CD, actual.getGetAppearanceCivilResourceResponse().getResponseCd());
        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_MSG, actual.getGetAppearanceCivilResourceResponse().getResponseMessageTxt());
        Assertions.assertEquals(1, actual.getGetAppearanceCivilResourceResponse().getResource().size());

        actual.getGetAppearanceCivilResourceResponse().getResource().stream().forEach(item -> {


            Assertions.assertEquals(RESOURCEID, item.getResourceId());
            Assertions.assertEquals(COURTROOMCD, item.getCourtRoomCd());
            Assertions.assertEquals(COURTAGENCYID, item.getCourtAgencyId());
            Assertions.assertEquals(BOOKINGSEQNO, item.getBookingSeqNo());
            Assertions.assertEquals(BOOKINGID, item.getBookingId());
            Assertions.assertEquals(BOOKINGCOMMENTTXT, item.getBookingCommentTxt());
            Assertions.assertEquals(BOOKINGCCN, item.getBookingCcn());
            Assertions.assertEquals(BOOKEDTOTM, item.getBookedToTm());
            Assertions.assertEquals(BOOKEDFROMTM, item.getBookedFromTm());
            Assertions.assertEquals(BOOKEDFORROLECD, item.getBookedForRoleCd());
            Assertions.assertEquals(BOOKEDBYNM, item.getBookedByNm());
            Assertions.assertEquals(AssetUsageRuleType.FIX, item.getAssetUsageRuleCd());
            Assertions.assertEquals(ASSETTYPECD, item.getAssetTypeCd());
            Assertions.assertEquals(RESOURCENM, item.getResourceNm());

        });

    }

    @DisplayName(TestHelpers.CASE_2 + ": When the PCSS api returns an error")
    @Test
    public void withErrorShouldReturnResult() {

        GetAppearanceCivilResourceRequest request = new GetAppearanceCivilResourceRequest();

        ca.bc.gov.open.pcss.civil.GetAppearanceCivilResourceRequest request2 = new ca.bc.gov.open.pcss.civil.GetAppearanceCivilResourceRequest();

        request2.setAppearanceId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);
        request2.setRequestAgencyIdentifierId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);

        request2.setAppearanceId(TestHelpers.CASE_2);

        request.setGetAppearanceCivilResourceRequest(request2);

        GetAppearanceCivilResourceResponse2 actual =  sut.getAppearanceCivilResource(request);

        Assertions.assertEquals(TestHelpers.ERROR_RESPONSE_CD, actual.getGetAppearanceCivilResourceResponse().getResponseCd());
        Assertions.assertEquals(TestHelpers.ERROR_RESPONSE_MSG, actual.getGetAppearanceCivilResourceResponse().getResponseMessageTxt());
        Assertions.assertEquals(0, actual.getGetAppearanceCivilResourceResponse().getResource().size());


    }

    private SearchFileAppearanceResourcesResponse getFakeAppearanceCivilResource() {


        SearchFileAppearanceResourcesResponse response = new SearchFileAppearanceResourcesResponse();

        response.setResponseCd(BigDecimal.valueOf(Integer.valueOf(TestHelpers.SUCCESS_RESPONSE_CD)));
        response.setResponseMsg(TestHelpers.SUCCESS_RESPONSE_MSG);

        SearchFileAppearanceResourcesData item = new SearchFileAppearanceResourcesData();
        
        item.setResourceid(RESOURCEID);
        item.setCourtroomcd(COURTROOMCD);
        item.setCourtagencyid(COURTAGENCYID);
        item.setBookingseqno(BOOKINGSEQNO);
        item.setBookingid(BOOKINGID);
        item.setBookingcommenttxt(BOOKINGCOMMENTTXT);
        item.setBookingccn(BOOKINGCCN);
        item.setBookedtotm(BOOKEDTOTM);
        item.setBookedfromtm(BOOKEDFROMTM);
        item.setBookedforrolecd(BOOKEDFORROLECD);
        item.setBookedfordt(BOOKEDFORDT);
        item.setBookedbynm(BOOKEDBYNM);
        item.setAssetusagerulecd(ASSETUSAGERULECD);
        item.setAssettypecd(ASSETTYPECD);
        item.setResourcenm(RESOURCENM);
        
        response.addDataItem(item);

        return response; 
    }
    

    private SearchFileAppearanceResourcesResponse getFakeErrorAppearanceCivilResource() {

        SearchFileAppearanceResourcesResponse response = new SearchFileAppearanceResourcesResponse();

        response.setResponseCd(BigDecimal.valueOf(Integer.valueOf(TestHelpers.ERROR_RESPONSE_CD)));
        response.setResponseMsg(TestHelpers.ERROR_RESPONSE_MSG);

        return response;

    }



}
