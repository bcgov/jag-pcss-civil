const getSyncCivilAppearance = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
        <v1:getSyncCivilAppearance>
            <getSyncCivilAppearanceRequest>
                <ns:getSyncCivilAppearanceRequest>
                    <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
                    <ns:RequestPartId>83.0001</ns:RequestPartId>
                    <ns:RequestDtm>2021-09-14T09:26:56.6</ns:RequestDtm>
                    <ns:ProcessUpToDtm>2021-09-14 09:26:56.6</ns:ProcessUpToDtm>
                </ns:getSyncCivilAppearanceRequest>
            </getSyncCivilAppearanceRequest>
        </v1:getSyncCivilAppearance>
    </soap:Body>
</soap:Envelope>`

const setHearingRestrictionCivil = `
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
        <v1:setHearingRestrictionCivil>
            <setHearingRestrictionCivilRequest>
                <ns:setHearingRestrictionCivilRequest>
                    <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
                    <ns:RequestPartId>19014.0001</ns:RequestPartId>
                    <ns:RequestDtm>2013-02-07 13:15:12.0</ns:RequestDtm>
                    <ns:OperationModeCd>UPDATE</ns:OperationModeCd>
                    <!--Optional:-->
                    <ns:HearingRestrictionId>7063</ns:HearingRestrictionId>
                    <!--Optional:-->
                    <ns:AdjudicatorPartId>12910.0026</ns:AdjudicatorPartId>
                    <!--Optional:-->
                    <ns:HearingRestrictionCd>A</ns:HearingRestrictionCd>
                    <!--Optional:-->
                    <ns:PhysicalFileId>706</ns:PhysicalFileId>
                    <!--Optional:-->
                    <ns:CivilDocumentId></ns:CivilDocumentId>
                    <!--Optional:-->
                    <ns:HearingRestrictionCcn>111111111111</ns:HearingRestrictionCcn>
                </ns:setHearingRestrictionCivilRequest>
            </setHearingRestrictionCivilRequest>
        </v1:setHearingRestrictionCivil>
    </soap:Body>
</soap:Envelope>`

const getFileDetailCivil = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
        <v1:getFileDetailCivil>
            <getFileDetailCivilRequest>
                <ns:getFileDetailCivilRequest>
                    <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
                    <ns:RequestPartId>83.0001</ns:RequestPartId>
                    <ns:RequestDtm>2021-09-14T09:26:56.6</ns:RequestDtm>
                    <ns:PhysicalFileId>8000005</ns:PhysicalFileId>
                </ns:getFileDetailCivilRequest>
            </getFileDetailCivilRequest>
        </v1:getFileDetailCivil>
    </soap:Body>
</soap:Envelope>`

export default {
  getSyncCivilAppearance,
  setHearingRestrictionCivil,
  getFileDetailCivil
}
