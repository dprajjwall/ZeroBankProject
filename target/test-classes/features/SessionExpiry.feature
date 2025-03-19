Feature: Session Expiry After Logout

  @expire
  Scenario: Verify user session expires after logout
    Given I have successfully logged into Zero Bank
    When I select the Online Banking option
    And I proceed to the Account Summary section
    And I log out from the Account Summary page
    And I attempt to navigate back using the browser's Back button
    Then I should be redirected to the login page or see a session timeout message
