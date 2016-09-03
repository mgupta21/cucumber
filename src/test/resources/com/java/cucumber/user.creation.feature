# Created by mgupta at 9/3/16
Feature: Account creation feature

  Scenario Outline: Password Validation
    Given I try to create an account with password "<Password>"
    Then I should see that the password is <Valid or Invalid>

    Examples: Too short
    Passwords are invalid if less than 4 characters
      | Password | Valid or Invalid |
      | abc      | invalid          |
      | ab1      | invalid          |

    Examples: Letters and Numbers
    Passwords need both letters and numbers to be valid
      | Password | Valid or Invalid |
      | abc1     | valid            |
      | abcd     | invalid          |
      | abcd1    | valid            |
      | 1abcd    | valid            |
      | ab1cd    | valid            |
      | 1111     | invalid          |