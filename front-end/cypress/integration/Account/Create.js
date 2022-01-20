describe('Navigation testing!', function () {
    it('redirects and confirms the page', function () {
        cy.visit('http://localhost:3000')

        cy.contains('Options').click()

        cy.contains('View').click()

        cy.url()
            .should('include', '/ViewPoll')
    })
})