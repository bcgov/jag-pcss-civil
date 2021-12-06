const getSyncCivilAppearance = '<?xml version="1.0" encoding="utf-8" ?><env:Envelope xmlns:env="http://www.w3.org/2003/05/soap-envelope"><env:Header/><env:Body><ser-root:getSyncCivilAppearanceResponse xmlns:ns4="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0" xmlns:ser-root="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"><getSyncCivilAppearanceResponse><ns4:getSyncCivilAppearanceResponse><ns4:ResponseCd>1</ns4:ResponseCd><ns4:ResponseMessageTxt>No change detected for any future civil appearance</ns4:ResponseMessageTxt></ns4:getSyncCivilAppearanceResponse></getSyncCivilAppearanceResponse></ser-root:getSyncCivilAppearanceResponse></env:Body></env:Envelope>'
const setHearingRestrictionCivil = '<?xml version="1.0" encoding="utf-8" ?><env:Envelope xmlns:env="http://www.w3.org/2003/05/soap-envelope"><env:Header/><env:Body><ser-root:setHearingRestrictionCivilResponse xmlns:ns4="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0" xmlns:ser-root="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"><setHearingRestrictionCivilResponse><ns4:setHearingRestrictionCivilResponse><ns4:ResponseCd>0</ns4:ResponseCd><ns4:HearingRestrictionCcn>245947851966</ns4:HearingRestrictionCcn></ns4:setHearingRestrictionCivilResponse></setHearingRestrictionCivilResponse></ser-root:setHearingRestrictionCivilResponse></env:Body></env:Envelope>'
const getFileDetailCivil = '<?xml version="1.0" encoding="utf-8" ?><env:Envelope xmlns:env="http://www.w3.org/2003/05/soap-envelope"><env:Header/><env:Body><ser-root:getFileDetailCivilResponse xmlns:ns4="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0" xmlns:ser-root="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"><getFileDetailCivilResponse><ns4:getFileDetailCivilResponse><ns4:ResponseCd>0</ns4:ResponseCd><ns4:PhysicalFileId>8000005</ns4:PhysicalFileId><ns4:FileNumberTxt>V-9000005</ns4:FileNumberTxt><ns4:HomeLocationAgenId>1.0001</ns4:HomeLocationAgenId><ns4:CourtLevelCd>S</ns4:CourtLevelCd><ns4:CourtClassCd>V</ns4:CourtClassCd><ns4:Party><ns4:PartyId>1000031</ns4:PartyId><ns4:LastNm>Borges</ns4:LastNm><ns4:GivenNm>Jorge</ns4:GivenNm><ns4:RoleTypeCd>DEF</ns4:RoleTypeCd><ns4:LeftRightCd>R</ns4:LeftRightCd><ns4:SelfRepresentedYN>N</ns4:SelfRepresentedYN><ns4:Counsel><ns4:CounselId>13035</ns4:CounselId><ns4:FullNm>Hermsen, Sarah</ns4:FullNm><ns4:PhoneNumberTxt>250 555 5555</ns4:PhoneNumberTxt></ns4:Counsel></ns4:Party><ns4:Party><ns4:PartyId>1000030</ns4:PartyId><ns4:LastNm>McCoy</ns4:LastNm><ns4:GivenNm>Vern</ns4:GivenNm><ns4:RoleTypeCd>PLA</ns4:RoleTypeCd><ns4:LeftRightCd>R</ns4:LeftRightCd><ns4:SelfRepresentedYN>N</ns4:SelfRepresentedYN></ns4:Party><ns4:Document><ns4:CivilDocumentId>3000005</ns4:CivilDocumentId><ns4:FileSeqNo>1</ns4:FileSeqNo><ns4:DocumentTypeCd>WRS</ns4:DocumentTypeCd><ns4:FiledDt>21-JAN-03</ns4:FiledDt><ns4:LastAppearanceId>64</ns4:LastAppearanceId><ns4:LastAppearanceDt>11-APR-03</ns4:LastAppearanceDt><ns4:LastAppearanceTm>01-APR-03</ns4:LastAppearanceTm><ns4:Issue><ns4:IssueNumber>1</ns4:IssueNumber><ns4:IssueTypeCd>CUST</ns4:IssueTypeCd><ns4:IssueDsc>Custody for Caveat</ns4:IssueDsc><ns4:ConcludedYn>N</ns4:ConcludedYn></ns4:Issue><ns4:Issue><ns4:IssueNumber>2</ns4:IssueNumber><ns4:IssueTypeCd>ACCS</ns4:IssueTypeCd><ns4:IssueDsc>Access for caveat</ns4:IssueDsc><ns4:ConcludedYn>N</ns4:ConcludedYn></ns4:Issue><ns4:Issue><ns4:IssueNumber>3</ns4:IssueNumber><ns4:IssueTypeCd>MANT</ns4:IssueTypeCd><ns4:IssueDsc>Maintenance for Caveat</ns4:IssueDsc><ns4:ConcludedYn>N</ns4:ConcludedYn></ns4:Issue><ns4:Issue><ns4:IssueNumber>4</ns4:IssueNumber><ns4:IssueTypeCd>CUST</ns4:IssueTypeCd><ns4:IssueDsc>Custody of what</ns4:IssueDsc><ns4:ConcludedYn>N</ns4:ConcludedYn></ns4:Issue></ns4:Document><ns4:Document><ns4:CivilDocumentId>131</ns4:CivilDocumentId><ns4:FileSeqNo>2</ns4:FileSeqNo><ns4:DocumentTypeCd>NCL</ns4:DocumentTypeCd><ns4:FiledDt>01-MAR-03</ns4:FiledDt><ns4:ConcludedYn>N</ns4:ConcludedYn><ns4:LastAppearanceId>64</ns4:LastAppearanceId><ns4:LastAppearanceDt>11-APR-03</ns4:LastAppearanceDt><ns4:LastAppearanceTm>01-APR-03</ns4:LastAppearanceTm><ns4:Issue><ns4:IssueNumber>1</ns4:IssueNumber><ns4:IssueTypeCd>CUST</ns4:IssueTypeCd><ns4:IssueDsc>Custody for claim</ns4:IssueDsc><ns4:ConcludedYn>N</ns4:ConcludedYn></ns4:Issue><ns4:Issue><ns4:IssueNumber>2</ns4:IssueNumber><ns4:IssueTypeCd>MANT</ns4:IssueTypeCd><ns4:IssueDsc>Maintenance for claim</ns4:IssueDsc><ns4:ConcludedYn>N</ns4:ConcludedYn></ns4:Issue></ns4:Document><ns4:Document><ns4:CivilDocumentId>132</ns4:CivilDocumentId><ns4:FileSeqNo>3</ns4:FileSeqNo><ns4:DocumentTypeCd>NOM</ns4:DocumentTypeCd><ns4:FiledDt>02-MAR-03</ns4:FiledDt><ns4:ConcludedYn>N</ns4:ConcludedYn><ns4:LastAppearanceId>64</ns4:LastAppearanceId><ns4:LastAppearanceDt>11-APR-03</ns4:LastAppearanceDt><ns4:LastAppearanceTm>01-APR-03</ns4:LastAppearanceTm></ns4:Document></ns4:getFileDetailCivilResponse></getFileDetailCivilResponse></ser-root:getFileDetailCivilResponse></env:Body></env:Envelope>'

export default {
  getSyncCivilAppearance,
  setHearingRestrictionCivil,
  getFileDetailCivil
}
