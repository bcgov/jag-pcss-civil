describe('Health Controller Tests', () => {
  it('Test Actuator Health Api', () => {
    cy.request({
      url: Cypress.env('pcss_civil_host') + 'actuator/health',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      expect(response.body.status).to.eq('UP')
    })
  })

  it('Test Health Api', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
      <v1:getHealth/>
    </soap:Body>
    </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'civil/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/getHealthV1.xml').should('eq', response.body)
    })
  })

  it('Test Ping Api', () => {
    const payload = `<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:v1="http://courts.gov.bc.ca/xml/ns/pcss/civil/v1" xmlns:ns="http://courts.gov.bc.ca/XMLSchema/PCSS/1.0.0">
    <soap:Header/>
    <soap:Body>
      <v1:getPing/>
    </soap:Body>
    </soap:Envelope>`

    cy.request({
      url: Cypress.env('pcss_civil_host') + 'civil/',
      body: payload,
      method: 'POST',
      headers: {
        authorization: Cypress.env('pcss_civil_token')
      }
    }).then((response) => {
      expect(response.status).to.eq(200)
      cy.readFile('./cypress/ExampleRequests/getPingV1.xml').should('eq', response.body)
    })
  })
})
