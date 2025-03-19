Feature: Add a New Payee

  @NewPayee
  Scenario: Successfully Add a New Payee
    Given User has logged into the banking application and navigates to Add New Payee page
    When User enters "John Doe" as the payee name
    And User enters "123 Main Street, NY" as the payee address
    And User enters "savings" as the payee account
    And User enters "Electricity Bill Payment" as the payee details
    And User clicks the Add button
    Then User should see a new payee confirmation message "The new payee John Doe was successfully created."