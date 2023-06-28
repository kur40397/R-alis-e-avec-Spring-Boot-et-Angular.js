package com.digital_banking.bank.Exception;

public class BalanceNotSufficentException extends Exception {
    public BalanceNotSufficentException(String balanceNotSuffisient) {
        super(balanceNotSuffisient);
    }
}
