Feature: Login to OrangeHRMS application
 
  @smoke
  Scenario Outline: login with credentials
    Given User is on HRMSLogin page
    When User enters username as "<username>" and "<password>"
    Then user should see the validation status as "<expectedResult>"
 
    Examples:

      | username | password | expectedResult |
      | Admin    | admin123 | Pass           |  # Row 1: Passes cleanly
      | admin    | test123  | Fail           |  # Row 2: Passes cleanly (Negative scenario)
      | Admin    | admin123 | Fail           |  # Row 3: FORCED DEFECT CHECK (Generates Screenshot)
