Feature: Schedule a Future Bill Payment

  @ScheduledPayment
  Scenario: Successfully scheduling a future-dated bill payment
    Given User has logged in and accessed the Bill Payment page
    When User selects "Apple" as the payee for future bill
    And User selects "Checking" as the account to be debited
    And User enters amount "500" and picks a future date "2025-07-10"
    And User enters "Subscription Renewal" in the description
    And User clicks on the pay button to schedule payment
    Then User should see a confirmation message "The bill payment has been scheduled successfully."
