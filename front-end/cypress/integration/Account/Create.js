describe('User functions', function () {
    it('registers new people', function () {
        cy.visit('http://localhost:3000')

        cy.getCookie('Voter').should('exist')

        cy.contains('Options').click()

        cy.contains('View').click()

        cy.url()
            .should('include', '/ViewPoll')
    })
})