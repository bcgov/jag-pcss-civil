import requestPayload from '../ExampleRequests/secure_endpoint_xml_requests'
import responsePayload from '../ExampleRequests/secure_endpoint_xml_responses'

describe('Secure Endpoint Controller Tests', () => {
  [
    'getAppearanceCivilApprMethodSecure',
    'getAppearanceCivilPartySecure',
    'getAppearanceCivilSecure',
    'getFileDetailCivilSecure'
  ].forEach(item => {
    it(`Should test the ${item} method return 200 with expected body`, () => {
      cy.request({
        url: Cypress.env('pcss_civil_host') + 'ws/',
        body: requestPayload[item],
        method: 'POST',
        headers: {
          authorization: Cypress.env('pcss_civil_token')
        }
      }).then(response => {
        expect(response.status).to.eq(200)
        expect(response.body).to.be.equal(responsePayload[item])
      })
    })
  })
})
