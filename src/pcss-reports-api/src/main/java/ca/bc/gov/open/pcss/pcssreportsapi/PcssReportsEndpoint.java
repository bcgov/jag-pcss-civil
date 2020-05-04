package ca.bc.gov.open.pcss.pcssreportsapi;


import bcgov.reeks.justicepcsscommon_wsprovider.pcssreport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PcssReportsEndpoint implements PcssReportPortType {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public GetJustinReportResponse2 getJustinReport(GetJustinReportRequest getJustinReportRequest) {
        ObjectFactory factory = new ObjectFactory();
        GetJustinReportResponse2 response = factory.createGetJustinReportResponse2();

        ca.bc.gov.courts.xmlschema.pcss._1_0.GetJustinReportResponse res = new ca.bc.gov.courts.xmlschema.pcss._1_0.GetJustinReportResponse();
        response.setGetJustinReportResponse(res);

        return response;
    }

    @Override
    public GetJustinReportAdobeResponse getJustinAdobeReport(GetJustinReportAdobeRequest getJustinAdobeReportRequest) {
        ObjectFactory factory = new ObjectFactory();
        GetJustinReportAdobeResponse response = factory.createGetJustinReportAdobeResponse();

        return response;
    }
}
