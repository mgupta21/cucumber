package com.java.cucumber.impl;

/**
 * Created by mgupta on 7/27/16.
 */
public class Account {

    private String accountHolderName;
    private long   accountNumber;
    private int    balance;
    // private CreditCard creditCard;

    public Account(String accountHolderName, int amount) {
        this.accountHolderName = accountHolderName;
        this.balance = amount;
        this.accountNumber = System.currentTimeMillis();
    }

    public Account(String accountHolderName, long accountNumber, int amount) {
        this.accountHolderName = accountHolderName;
        this.balance = amount;
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void withdraw(int amount, long cardNumber, int pinNumber) {
        if (CreditCardMatcher.isPinValid(cardNumber, pinNumber)) {
            System.out.println("Successfully withDrew amount : " + amount);
            withdraw(amount);
        } else {
            System.out.println("Error : Invalid PIN");
        }
    }

    /*
     * public void withdrawByCreditCard(int amount, CreditCard creditCard) {
     * if (!hasCreditCard()) {
     * throw new NoCreditCardAssoiciatedWithAccount();
     * }
     * isLinkedCreditCard(creditCard);
     * getCreditCard().withdraw(amount);
     * }
     */

    /*
     * private boolean isLinkedCreditCard(CreditCard creditCard) {
     * return this.getCreditCard().getCardNumber() == creditCard.getCardNumber() && this.getCreditCard().getCardPinNumber() ==
     * creditCard.getCardPinNumber()
     * && this.getCreditCard().getExpirationDate() == creditCard.getExpirationDate();
     * }
     */

    /*
     * private boolean hasCreditCard() {
     * return this.creditCard != null;
     * }
     */

    /*
     * public void addCreditCard(CreditCard creditCard) {
     * this.creditCard = creditCard;
     * }
     */

    /*
     * public CreditCard getCreditCard() {
     * return creditCard;
     * }
     */
}
