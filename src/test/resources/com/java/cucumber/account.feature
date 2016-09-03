# Created by mgupta at 9/2/16
Feature: Account feature

  Scenario: Successful withdrawal from an account
    Given I have balance of $100 in my account
    When I request $20
    Then $20 should be dispensed