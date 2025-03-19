Feature: Download Account Statement
  
  @download
  Scenario: User downloads an account statement
    Given User is logged in and on the Statements & Documents page
    And User clicks to download the statement
    Then The download button should be clicked
