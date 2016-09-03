@CreditCard
Feature: Feedback on entering credit card details

  Scenario: Credit card number too short
    Given I have choosen some items to buy
    When I enter a card number that's only four 1234 digits long
    And all the other details are correct
    And I submit the form
    Then invalid card number error should be displayed

  Scenario: Credit card expired
    Given I have choosen some items to buy
    When I enter a card expiry date that's in the past
    And all the other details are correct
    And I submit the form
    Then card expiration error should be displayed

  Scenario: Change the PIN successfully
    Given I have been issued a new card
    And I insert the card, entering the correct PIN
    When I choose "Change PIN" from the menu
    And I change the PIN to 9876
    Then the system should remember my PIN is now 9876

  Scenario: Change the PIN unsuccessfully
    Given I have been issued a new card
    And I insert the card, entering the correct PIN
    When I choose "Change PIN" from the menu
    And I try to change the PIN to the original PIN number
    Then I should see a warning message
    Then the system should not have changed my PIN