Feature: Negative Amount Fund Transfer

  @negative_amount
  Scenario: Attempt to transfer a negative amount
    Given User has logged into the banking application for negative amount transfer
    And User navigates to the Fund Transfer page for negative amount transfer
    When User selects "Savings(Avail. balance = $ 1000)" as the source account for negative amount transfer
    And User selects "Checking(Avail. balance = $ -500.2)" as the destination account for negative amount transfer
    And User enters negative transfer amount "-500" and description "Invalid negative transfer"
    And User clicks on continue button for negative amount transfer
    Then User should see the error message "Invalid amount entered" for negative amount transfer
