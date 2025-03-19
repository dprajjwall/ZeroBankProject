Feature: Fund Transfer Functionality

  @Fund
  Scenario: Successful fund transfer between accounts
    Given User has logged into the banking application for regular transfer
    And User navigates to the Fund Transfer page for regular transfer
    When User selects "Savings(Avail. balance = $ 1000)" as the source account for regular transfer
    And User selects "Checking(Avail. balance = $ -500.2)" as the destination account for regular transfer
    And User enters transfer amount "500" and description "Transfer for rent" for regular transfer
    And User clicks on continue button for regular transfer
    And User reviews the transfer details and submits for regular transfer
    Then User should see the success message "You successfully submitted your transaction." for regular transfer

