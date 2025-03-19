Feature: Verifying Account types
	@VerifyingAccounttypes
  Scenario: Verify that all expected account types are displayed
    Given User logs into Zero Bank for Account Summary
    When User navigates to the Account Summary page
    Then User should see all expected account types