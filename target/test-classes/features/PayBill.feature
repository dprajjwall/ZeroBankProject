Feature: PayBill Functionality

  @Bill
  Scenario: Successful fund transfer between accounts
    Given User has logged into the banking application and navigates to Pay Bill page
    When User selects "Apple" as the payee
    And User selects "Savings" as the account
    And User enters amount "1000" and date "2025-03-27"
    And User enters "Bill Payment" in description
    And User clicks on pay button for bill payment
    Then User should see the success message "The payment was successfully submitted."
    
    