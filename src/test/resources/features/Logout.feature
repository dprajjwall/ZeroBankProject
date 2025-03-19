Feature: User Logout

  @logout
  Scenario: Successful logout from Zero Bank
    Given User is logged into the Zero Bank application
    When User clicks on the logout button
    Then User should be logged out and redirected to the login page
