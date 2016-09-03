# Created by mgupta at 9/2/16
Feature: Withdraw money from account via creditcard

  Scenario: Attempt to withdrawal using invalid card
    Given I have $100 in my account
    But my card is invalid
    When I request $50 via invalid card
    Then I should get invalid card exception

  Scenario: Attempt to withdrawal using valid card
    Given I have $100 in my account
    And my card is valid
    When I request $50 via valid card
    Then I should get $50 withdrawn