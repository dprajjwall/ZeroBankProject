Feature: Login Functionality
	@Login
  Scenario: Successful Login
    Given User opens the Zero Bank login page
    When User enters valid username "username" and password "password"
    And Clicks on the Sign In button
    Then User should be redirected to the Account Summary page
