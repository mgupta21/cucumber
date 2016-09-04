# Created by mgupta at 9/2/16
Feature: Account feature

#  Scenario: Successful withdrawal from an account
#    Given I have "$100 in my account
#    When I choose to withdraw the fixed amount of "$20
#    Then the balance of my account should be "$80
#
#  Scenario: Unsuccessful withdrawal from an account
#    Given I have "$100 in my account
#    When I choose to withdraw the fixed amount of "$101
#    Then I should see an error message
#    And the balance of my account should be "$100

  Scenario Outline: Withdraw fixed amount
    Given I have <Balance> in my account
    When I choose to withdraw the fixed amount of <withdrawal>
    Then I should <Outcome>
    And the balance of my account should be <Remaining>

    Examples: Successful withdrawal
      | Balance | withdrawal | Remaining | Outcome             |
      | "$500"  | "$50"      | "$450"    | receive "$50" cash  |
      | "$500"  | "$100"     | "$400"    | receive "$100" cash |
      | "$500"  | "$200"     | "$300"    | receive "$200" cash |

    Examples: Unsuccessful withdrawal
      | Balance | withdrawal | Remaining | Outcome              |
      | "$100"  | "$200"     | "$100"    | see an error message |
      | "$0"    | "$50"      | "$0"      | see an error message |

