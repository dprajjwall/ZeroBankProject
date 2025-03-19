Feature: Pay Bills Without Entering Amount

  @NegativeTest
  Scenario: Attempting to pay a bill without entering the amount
    Given User is on the Pay Bill page
    When User selects "Sprint" as the payee without entering amount
    And User selects "Savings" as the account without entering amount
    And User leaves the amount field empty
    And User clicks on the pay button
    Then User should see an error message "Please fill out this field."
