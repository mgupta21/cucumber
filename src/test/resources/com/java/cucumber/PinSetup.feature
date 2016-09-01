# Created by mgupta at 8/31/16
Feature: Change PIN

  Scenario: Change the PIN successfully
    Given I have been issued a new card
    And I insert the card, entering the correct PIN
    When I choose "Change PIN" from the menu
    And I change the PIN to 9876
    Then the system should remember my PIN is now 9876