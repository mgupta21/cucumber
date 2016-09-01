Feature: Feedback on entering credit card details

  Background:
    Given I have choosen some items to buy
    And I am about to enter my credit card details

  Scenario: Credit card number too short
    When I enter a card number that's only four 1234 digits long
    And all the other details are correct
    And I submit the form
    Then invalid card number error should be displayed

  Scenario: Expiry Date must not be in the past
    When I enter a card expiry date that's in the past
    And all the other details are correct
    And I submit the form
    Then card expiration error should be displayed

  Scenario: Successful withdrawal from an account
    Given I have $100 in my account
    When I request $20
    Then $20 should be dispensed

  Scenario: Attempt to withdrawal using stolen card
    Given I have $100 in my account
    But my card is invalid
    When I request $50 via invalid card
    Then I should get invalid card exception

  Scenario: Attempt to withdrawal using stolen card
    Given I have $100 in my account
    But my card is valid
    When I request $50 via valid card
    Then I should get $50 withdrawn