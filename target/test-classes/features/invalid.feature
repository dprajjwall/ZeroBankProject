Feature: Invalid Login

  @InvalidLogin
  Scenario: User enters incorrect credentials
    Given User opens the Zero Bank login page for Invalid Login
    When User enters invalid username "wrongUser" and password "wrongPass" for Invalid Login
    And Clicks on the Sign In button for Invalid Login
    Then User should see an error message "Login and/or password are wrong." for Invalid Login
