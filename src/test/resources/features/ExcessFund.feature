Feature: Excess Fund Transfer 

  @excess
  Scenario: Excess fund transfer between accounts
    Given User has logged into the banking application for excess fund transfer
    And User navigates to the Fund Transfer page for excess fund transfer
    When User selects "Savings(Avail. balance = $ 1000)" as the source account for excess fund transfer
    And User selects "Checking(Avail. balance = $ -500.2)" as the destination account for excess fund transfer
    And User enters transfer amount "2000" and description "Transfer for rent" for excess fund transfer
    And User clicks on continue button for excess fund transfer
    And User reviews the transfer details and submits for excess fund transfer
    Then User should see the error message "Insufficient funds" for excess fund transfer
