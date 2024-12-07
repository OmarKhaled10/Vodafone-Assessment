Feature: feature to test Signing Up Functionality in demoblaze.com

  Scenario: Verify that the Sign Up successful appears , After the user enters valid credentials

    Given user is on demoblaze web home page
    And the user presses sign up in homepage
    When the user enters valid Credentials
    And the user presses sign up from the pop up window
    Then Sign Up successful appears
