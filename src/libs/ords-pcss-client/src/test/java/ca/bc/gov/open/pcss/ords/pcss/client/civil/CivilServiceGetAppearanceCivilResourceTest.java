package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResourcesData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResourcesResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@DisplayName("CivilService: gvilSearchFileAppearanceDocument test suite")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CivilServiceGetAppearanceCivilResourceTest {


    private static final String ASSETTYPECD = "setAssettypecd";
    private static final String ASSETUSAGERULECD = "setAssetusagerulecd";
    private static final String BOOKEDBYNM = "Bookedbynm";
    private static final String BOOKEDFORDT = "Bookedfordt";
    private static final String BOOKEDFORROLECD = "Bookedforrolecd";
    private static final String BOOKEDFROMTM = "Bookedfromtm";
    private static final String BOOKEDTOTM = "Bookedtotm";
    private static final String BOOKINGCCN = "Bookingccn";
    private static final String BOOKINGCOMMENTTXT = "Bookingcommenttxt";
    private static final String BOOKINGID = "Bookingid";
    private static final String BOOKINGSEQNO = "Bookingseqno";
    private static final String COURTAGENCYID = "Courtagencyid";
    private static final String COURTROOMCD = "Courtroomcd";
    private static final String RESOURCEID = "Resourceid";
    private CivilServiceImpl sut;

    @Mock
    private PcssCivilApi pcssCivilApiMock;

    @BeforeEach
    public void setUp() throws ApiException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceResourcesGet(TestHelpers.CASE_3)).thenThrow(TestHelpers.DEFAULT_EXCEPTION);

        sut = new CivilServiceImpl(pcssCivilApiMock);
    }

    @DisplayName(TestHelpers.CASE_1 + ": When api return 1 document")
    @Test
    public void withOneDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_1, 1);
    }

    @DisplayName(TestHelpers.CASE_2 + ": When api return 100 document")
    @Test
    public void withMultipleDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_2, 100);
    }

    @DisplayName(TestHelpers.CASE_3 + ": When api error")
    @Test
    public void withApiExceptionShouldReturnResult() throws ApiException {

        SearchFileAppearanceResourcesResponse actual = sut.getAppearanceCivilResource(TestHelpers.CASE_3);

        Assertions.assertEquals(TestHelpers.DEFAULT_ERROR_RESPONSE_CD, actual.getResponseCd());
        Assertions.assertEquals(TestHelpers.RESPONSE_BODY, actual.getResponseMsg());
        Assertions.assertEquals(null, actual.getData());

    }

    private void testSuccess(String testCase, int documentCount) throws ApiException {


        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceResourcesGet(Mockito.eq(testCase)))
                .thenReturn(getFakeResponse(documentCount));

        SearchFileAppearanceResourcesResponse actual = sut.getAppearanceCivilResource(testCase);

        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_CD, actual.getResponseCd());
        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_MSG, actual.getResponseMsg());

        Assertions.assertEquals(documentCount, actual.getData().size());
        
        actual.getData().stream().forEach(item -> {

            Assertions.assertEquals(ASSETTYPECD, item.getAssettypecd());
            Assertions.assertEquals(ASSETUSAGERULECD, item.getAssetusagerulecd());
            Assertions.assertEquals(BOOKEDBYNM, item.getBookedbynm());
            Assertions.assertEquals(BOOKEDFORDT, item.getBookedfordt());
            Assertions.assertEquals(BOOKEDFORROLECD, item.getBookedforrolecd());
            Assertions.assertEquals(BOOKEDFROMTM, item.getBookedfromtm());
            Assertions.assertEquals(BOOKEDTOTM, item.getBookedtotm());
            Assertions.assertEquals(BOOKINGCCN, item.getBookingccn());
            Assertions.assertEquals(BOOKINGCOMMENTTXT, item.getBookingcommenttxt());
            Assertions.assertEquals(BOOKINGID, item.getBookingid());
            Assertions.assertEquals(BOOKINGSEQNO, item.getBookingseqno());
            Assertions.assertEquals(COURTAGENCYID, item.getCourtagencyid());
            Assertions.assertEquals(COURTROOMCD, item.getCourtroomcd());
            Assertions.assertEquals(RESOURCEID, item.getResourceid());
            
        });
        

    }

    private SearchFileAppearanceResourcesResponse getFakeResponse(int documentCount) {


        SearchFileAppearanceResourcesResponse result = new SearchFileAppearanceResourcesResponse();

        result.setResponseCd(TestHelpers.SUCCESS_RESPONSE_CD);
        result.setResponseMsg(TestHelpers.SUCCESS_RESPONSE_MSG);
        
        for(int i =0; i < documentCount; i++) {

            SearchFileAppearanceResourcesData item = new SearchFileAppearanceResourcesData();
            
            item.setAssettypecd(ASSETTYPECD);
            item.setAssetusagerulecd(ASSETUSAGERULECD);
            item.setBookedbynm(BOOKEDBYNM);
            item.setBookedfordt(BOOKEDFORDT);
            item.setBookedforrolecd(BOOKEDFORROLECD);
            item.setBookedfromtm(BOOKEDFROMTM);
            item.setBookedtotm(BOOKEDTOTM);
            item.setBookingccn(BOOKINGCCN);
            item.setBookingcommenttxt(BOOKINGCOMMENTTXT);
            item.setBookingid(BOOKINGID);
            item.setBookingseqno(BOOKINGSEQNO);
            item.setCourtagencyid(COURTAGENCYID);
            item.setCourtroomcd(COURTROOMCD);
            item.setResourceid(RESOURCEID);
            
            
            result.addDataItem(item);
            
        }
        
        return result;

    }

}
