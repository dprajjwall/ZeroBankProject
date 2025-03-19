Feature: Empty Login Fields
	@EmptyLogin
  Scenario: User tries to login without entering credentials
    Given User opens the Zero Bank login page for Empty Login
    When User does not enter any credentials for Empty Login
    And Clicks on the Sign In button for Empty Login
    Then User should see an error message "Login and/or password are wrong." for Empty Login
