const getAppearanceCivilApprMethodSecure = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
        <v1:getAppearanceCivilApprMethodSecure>
            <getAppearanceCivilApprMethodSecureRequest>
                <ns:getAppearanceCivilApprMethodSecureRequest>
                    <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
                    <ns:RequestPartId>19014.0001</ns:RequestPartId>
                    <ns:RequestDtm>05-NOV-2021</ns:RequestDtm>
                    <ns:ApplicationCd>PCSS</ns:ApplicationCd>
                    <ns:AppearanceId>6090</ns:AppearanceId>
                </ns:getAppearanceCivilApprMethodSecureRequest>
            </getAppearanceCivilApprMethodSecureRequest>
        </v1:getAppearanceCivilApprMethodSecure>
    </soap:Body>
</soap:Envelope>`

const getAppearanceCivilPartySecure = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
        <v1:getAppearanceCivilPartySecure>
            <getAppearanceCivilPartySecureRequest>
                <ns:getAppearanceCivilPartySecureRequest>
                    <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
                    <ns:RequestPartId>19014.0001</ns:RequestPartId>
                    <ns:RequestDtm>03-Nov-2021</ns:RequestDtm>
                    <ns:ApplicationCd>PCSS</ns:ApplicationCd>
                    <ns:AppearanceId>6090</ns:AppearanceId>
                </ns:getAppearanceCivilPartySecureRequest>
            </getAppearanceCivilPartySecureRequest>
        </v1:getAppearanceCivilPartySecure>
    </soap:Body>
</soap:Envelope>`

const getAppearanceCivilSecure = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
        <v1:getAppearanceCivilSecure>
            <getAppearanceCivilSecureRequest>
                <ns:getAppearanceCivilSecureRequest>
                    <ns:RequestAgencyIdentifierId>8807.0001</ns:RequestAgencyIdentifierId>
                    <ns:RequestPartId>19014.0001</ns:RequestPartId>
                    <ns:RequestDtm>06-NOV-2021</ns:RequestDtm>
                    <ns:ApplicationCd>PCSS</ns:ApplicationCd>
                    <ns:PhysicalFileId>2486</ns:PhysicalFileId>
                    <ns:FutureYN>Y</ns:FutureYN>
                    <ns:HistoryYN>Y</ns:HistoryYN>
                </ns:getAppearanceCivilSecureRequest>
            </getAppearanceCivilSecureRequest>
        </v1:getAppearanceCivilSecure>
    </soap:Body>
</soap:Envelope>`

const getFileDetailCivilSecure = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
        <v1:getFileDetailCivilSecure>
            <getFileDetailCivilSecureRequest>
                <ns:getFileDetailCivilSecureRequest>
                    <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
                    <ns:RequestPartId>19014.0001</ns:RequestPartId>
                    <ns:RequestDtm>04-NOV-2021</ns:RequestDtm>
                    <ns:ApplicationCd>PCSS</ns:ApplicationCd>
                    <ns:PhysicalFileId>8000005</ns:PhysicalFileId>
                </ns:getFileDetailCivilSecureRequest>
            </getFileDetailCivilSecureRequest>
        </v1:getFileDetailCivilSecure>
    </soap:Body>
</soap:Envelope>`

export default {
  getAppearanceCivilApprMethodSecure,
  getAppearanceCivilPartySecure,
  getAppearanceCivilSecure,
  getFileDetailCivilSecure,
}