package ca.bc.gov.open.pcss.pcsswebservice;

import bcgov.reeks.justicepcsscommon_wsprovider.pcssreport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PcssReportEndpoint implements PcssReportPortType {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public GetJustinReportResponse2 getJustinReport(GetJustinReportRequest getJustinReportRequest) {
        return null;
    }

    @Override
    public GetJustinReportAdobeResponse getJustinAdobeReport(GetJustinReportAdobeRequest getJustinAdobeReportRequest) {
        return null;
    }
}
