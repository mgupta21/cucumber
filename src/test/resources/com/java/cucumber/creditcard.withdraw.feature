# Created by mgupta at 9/2/16
@CreditCard.Withdraw
Feature: Withdraw money from account via creditcard

  Scenario: Unsuccessful attempt to withdrawal using invalid card
    Given I have $100 in my account
    But my card is invalid
    When I request $50 via invalid card
    Then I should get invalid card exception

  Scenario: successful attempt to withdrawal using valid card
    Given I have $100 in my account
    And my card is valid
    When I request $50 via valid card
    Then I should get $50 withdrawn

  Scenario: Unsuccessful attempt to withdrawal using valid card
    Given I have $100 in my account
    And my card is valid
    When I request $200 via valid card
    Then Insufficient account balance warning is displayed
    And I should have $100 in my account

  Scenario: Attempt to withdrawal using multiple cards
    Given I have $100 in my account
    And I have a card A "1212121212121212" associated with my account
    And I have a card B "9191919191919191" associated with my account
    When I request $20 via card A
    And I request $70 via card B
    Then I should have $10 in my account