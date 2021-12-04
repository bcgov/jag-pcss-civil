describe('Secure Endpoint Controller Tests', () => {
  it('Should test the getAppearanceCivilApprMethodSecureRequest method return 200 with expected body', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
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
    cy.request({
      url: Cypress.env('pcss_civil_host') + 'ws/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then(response => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/getAppearanceCivilSecure.xml').should('eq', response.body)
    })
  })
})
