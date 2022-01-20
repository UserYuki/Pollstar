describe('User functions', function () {
    it('logs user in', function () {
        cy.visit('http://localhost:3000')

        cy.wait(2000)
        cy.getCookie('Voter').should('exist')

        cy.contains('Sign').click()
        cy.url().should('include', '/User')

        cy.get('[id="UserNameIn"]')
        .type('System')
        .should('have.value', 'System')
        
        cy.get('[id="PasswordIn"]')
        .type('Sys')
        .should('have.value', 'Sys')

        cy.get('.left')
        .children().find('Button').click()
    })
})
describe('User functions', function () {
    it('logs user in', function () {
        cy.visit('http://localhost:3000')

        cy.wait(2000)
        cy.getCookie('Voter').should('exist')

        cy.contains('Sign').click()
        cy.url().should('include', '/User')

        cy.get('[id="UserNameIn"]')
        .type('System')
        .should('have.value', 'System')
        
        cy.get('[id="PasswordIn"]')
        .type('Sys')
        .should('have.value', 'Sys')

        cy.get('.left')
        .children().find('Button').click()
    })
})