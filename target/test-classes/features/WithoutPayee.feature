Feature: Add New Payee

  @WithoutPayee
  Scenario: Add Payee with Missing Details
    Given User is logged in and on the Add New Payee page
    When User leaves the "Payee Name" field empty
    And User clicks the "Add" button
    Then User should see an error message "Please fill out this field." without payee
