package ca.bc.gov.open.pcss.pcsswebservice;

import ca.bc.gov.courts.xml.ns.cdds.v1.CddsPortType;
import ca.bc.gov.courts.xml.ns.cdds.v1.GetDigitalDisplayCourtListRequest;
import ca.bc.gov.courts.xml.ns.cdds.v1.GetDigitalDisplayCourtListResponse2;
import org.springframework.stereotype.Service;

@Service
public class JusticeCddsEndpoint implements CddsPortType {
    @Override
    public GetDigitalDisplayCourtListResponse2 getDigitalDisplayCourtList(GetDigitalDisplayCourtListRequest getDigitalDisplayCourtListRequest) {
        return null;
    }
}
