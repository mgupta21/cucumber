Feature: Feedback when entering invalid credit card details

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