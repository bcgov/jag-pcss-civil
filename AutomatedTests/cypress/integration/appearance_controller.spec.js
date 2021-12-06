describe('Appearance Controller Tests', () => {
  it('Test Get Appearance Civil', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
      <soap:Header/>
      <soap:Body>
      <v1:getAppearanceCivil>
      <getAppearanceCivilRequest>
      <ns:getAppearanceCivilRequest>
      <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
      <ns:RequestPartId>19014.0001</ns:RequestPartId>
      <ns:RequestDtm>01-Nov-12</ns:RequestDtm>
      <ns:PhysicalFileId>2486</ns:PhysicalFileId>
      <ns:FutureYN>Y</ns:FutureYN>
      <ns:HistoryYN>Y</ns:HistoryYN>
      </ns:getAppearanceCivilRequest>
      </getAppearanceCivilRequest>
      </v1:getAppearanceCivil>
      </soap:Body>
      </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/getAppearanceV1.xml').should('eq', response.body)
    })
  })

  it('Test Set Appearance Civil', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
      <soap:Header/>
      <soap:Body>
      <v1:setAppearanceCivil>
      <setAppearanceCivilRequest>
      <ns:setAppearanceCivilRequest>
      <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
      <ns:RequestPartId>19014.0001</ns:RequestPartId>
      <ns:RequestDtm>2021-09-14T09:26:56.6</ns:RequestDtm>
      <!--Zero or more repetitions:-->
      <ns:Detail>
      <ns:OperationMode>Update</ns:OperationMode>
      <ns:PcssAppearanceId>14723</ns:PcssAppearanceId>
      <ns:PhysicalFileId>2486</ns:PhysicalFileId>
      <ns:AppearanceDt>1998-01-01 01:30:00.0</ns:AppearanceDt>
      <!--Optional:-->
      <ns:AppearanceTm>1998-01-01 03:30:00.0</ns:AppearanceTm>
      <ns:CourtAgencyId>83.0001</ns:CourtAgencyId>
      <ns:CourtRoomCd>001</ns:CourtRoomCd>
      <!--Optional:-->
      <ns:EstimatedTimeHour>1</ns:EstimatedTimeHour>
      <!--Optional:-->
      <ns:EstimatedTimeMin>0</ns:EstimatedTimeMin>
      <!--Optional:-->
      <ns:SupplementalEquipmentTxt>supplemental_equipment_txt</ns:SupplementalEquipmentTxt>
      <!--Optional:-->
      <ns:SecurityRestrictionTxt>security_restriction_txt</ns:SecurityRestrictionTxt>
      <!--Optional:-->
      <ns:OutOfTownJudgeTxt>out_of_town_judge_txt</ns:OutOfTownJudgeTxt>
      <!--Optional:-->
      <ns:AppearanceId></ns:AppearanceId>
      <!--Optional:-->
      <ns:AppearanceCcn></ns:AppearanceCcn>
      <!--Zero or more repetitions:-->
      <ns:Document>
      <ns:OperationMode>UPDATE</ns:OperationMode>
      <ns:CivilDocumentId>1855</ns:CivilDocumentId>
      <!--Optional:-->
      <ns:AppearanceReasonCd>FCC</ns:AppearanceReasonCd>
      <!--Optional:-->
      <ns:EstimatedTimeHour>1</ns:EstimatedTimeHour>
      <!--Optional:-->
      <ns:EstimatedTimeMin>0</ns:EstimatedTimeMin>
      </ns:Document>
      <!--Zero or more repetitions:-->
      <ns:Party>
      <ns:OperationMode>ADD</ns:OperationMode>
      <ns:PartyId>666</ns:PartyId>
      </ns:Party>
      </ns:Detail>
      </ns:setAppearanceCivilRequest>
      </setAppearanceCivilRequest>
      </v1:setAppearanceCivil>
      </soap:Body>
      </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/setAppearanceCivilV1.xml').should('eq', response.body)
    })
  })

  it('Test Get Appearance Civil Appr Method', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
      <soap:Header/>
      <soap:Body>
      <v1:getAppearanceCivilApprMethod>
      <getAppearanceCivilApprMethodRequest>
      <ns:getAppearanceCivilApprMethodRequest>
      <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
      <ns:RequestPartId>83.0001</ns:RequestPartId>
      <ns:RequestDtm>2021-09-14T09:26:56.6</ns:RequestDtm>
      <ns:AppearanceId>6090</ns:AppearanceId>
      </ns:getAppearanceCivilApprMethodRequest>
      </getAppearanceCivilApprMethodRequest>
      </v1:getAppearanceCivilApprMethod>
      </soap:Body>
      </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/getAppearanceApprMethodV1.xml').should('eq', response.body)
    })
  })

  it('Test Set Appearance Method Civil', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
      <soap:Header/>
      <soap:Body>
      <v1:setAppearanceMethodCivil>
      <setAppearanceMethodCivilRequest>
      <ns:setAppearanceMethodCivilRequest>
      <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
      <ns:RequestPartId>19014.0001</ns:RequestPartId>
      <ns:RequestDtm>07-FEB-13</ns:RequestDtm>
      <!--Zero or more repetitions:-->
      <ns:Detail>
      <ns:OperationModeCd>ADD</ns:OperationModeCd>
      <ns:AppearanceId>14787</ns:AppearanceId>
      <!--Optional:-->
      <ns:OldRoleTypeCd></ns:OldRoleTypeCd>
      <!--Optional:-->
      <ns:OldAppearanceMethodCd></ns:OldAppearanceMethodCd>
      <!--Optional:-->
      <ns:ApprMethodCcn></ns:ApprMethodCcn>
      <!--Optional:-->
      <ns:NewRoleTypeCd>APP</ns:NewRoleTypeCd>
      <!--Optional:-->
      <ns:NewAppearanceMethodCd>VC</ns:NewAppearanceMethodCd>
      </ns:Detail>
      </ns:setAppearanceMethodCivilRequest>
      </setAppearanceMethodCivilRequest>
      </v1:setAppearanceMethodCivil>
      </soap:Body>
      </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/setAppearanceMethodCivilV1.xml').should('eq', response.body)
    })
  })

  it('Test Get Appearance Civil Document', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
      <soap:Header/>
      <soap:Body>
      <v1:getAppearanceCivilDocument>
      <getAppearanceCivilDocumentRequest>
      <ns:getAppearanceCivilDocumentRequest>
      <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
      <ns:RequestPartId>83.0001</ns:RequestPartId>
      <ns:RequestDtm>2021-09-14T09:26:56.6</ns:RequestDtm>
      <ns:AppearanceId>7093</ns:AppearanceId>
      </ns:getAppearanceCivilDocumentRequest>
      </getAppearanceCivilDocumentRequest>
      </v1:getAppearanceCivilDocument>
      </soap:Body>
      </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/getAppearanceCivilDocumentV1.xml').should('eq', response.body)
    })
  })

  it('Test Get Appearance Civil Party', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
      <soap:Header/>
      <soap:Body>
      <v1:getAppearanceCivilParty>
      <getAppearanceCivilPartyRequest>
      <ns:getAppearanceCivilPartyRequest>
      <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
      <ns:RequestPartId>83.0001</ns:RequestPartId>
      <ns:RequestDtm>2021-09-14T09:26:56.6</ns:RequestDtm>
      <!-- <ns:AppearanceId>6090</ns:AppearanceId> -->
      </ns:getAppearanceCivilPartyRequest>
      </getAppearanceCivilPartyRequest>
      </v1:getAppearanceCivilParty>
      </soap:Body>
      </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/getAppearanceCivilPartyV1.xml').should('eq', response.body)
    })
  })

  it('Test Get Appearance Civil Resource', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
    <v1:getAppearanceCivilResource>
    <getAppearanceCivilResourceRequest>
    <ns:getAppearanceCivilResourceRequest>
    <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
    <ns:RequestPartId>83.0001</ns:RequestPartId>
    <ns:RequestDtm>2021-09-14T09:26:56.6</ns:RequestDtm>
    <ns:AppearanceId>7093</ns:AppearanceId>
    </ns:getAppearanceCivilResourceRequest>
    </getAppearanceCivilResourceRequest>
    </v1:getAppearanceCivilResource>
    </soap:Body>
    </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/getAppearanceCivilResourceV1.xml').should('eq', response.body)
    })
  })

  it('Test Set Counsel Detail Civil', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
      <soap:Header/>
      <soap:Body>
      <v1:setCounselDetailCivil>
      <setCounselDetailCivilRequest>
      <ns:setCounselDetailCivilRequest>
      <ns:RequestAgencyIdentifierId>83.0001</ns:RequestAgencyIdentifierId>
      <ns:RequestPartId>19014.0001</ns:RequestPartId>
      <ns:RequestDtm>07-FEB-13</ns:RequestDtm>
      <ns:CivilPartyId>293</ns:CivilPartyId>
      <ns:SelfRepresentedYn>N</ns:SelfRepresentedYn>
      <!--Zero or more repetitions:-->
      <ns:Detail>
      <ns:OperationModeCd>ADD</ns:OperationModeCd>
      <ns:CounselLastNm>Johnson55</ns:CounselLastNm>
      <ns:CounselFirstNm>Kevinoo</ns:CounselFirstNm>
      <!--Optional:-->
      <ns:OfficePhoneNoTxt>250 882 1234</ns:OfficePhoneNoTxt>
      </ns:Detail>
      </ns:setCounselDetailCivilRequest>
      </setCounselDetailCivilRequest>
      </v1:setCounselDetailCivil>
      </soap:Body>
      </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/setCounselDetailCivilV1.xml').should('eq', response.body)
    })
  })
})
