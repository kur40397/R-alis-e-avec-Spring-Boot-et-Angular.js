package com.digital_banking.bank.service;

import com.digital_banking.bank.Exception.BalanceNotSufficentException;
import com.digital_banking.bank.Exception.BankAccountNotFoundException;
import com.digital_banking.bank.Exception.CustomerNotFoundException;
import com.digital_banking.bank.dto.*;
import com.digital_banking.bank.entities.BankAccount;
import com.digital_banking.bank.entities.Customer;

import java.util.List;

// hna radi tl9a des inputs throws une list de throws
public interface BankAccountService {
    // katsecifie les methods li radin n5admo bihoum f service
    // mn hna kanchofo achno nkado nidirou b la couche service
    // ach imkan la couche service tdir

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    // 5asak tkoun precis f type de retour
    // tretourni type fille
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId)
            throws CustomerNotFoundException;

    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId)
            throws CustomerNotFoundException;

    List<CustomerDTO> listCustomers();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String Desc) throws BalanceNotSufficentException;

    // creation d'un objet operation de type debit
    void credit(String accountId, double amount, String Desc) throws BalanceNotSufficentException;

    void transfer(String accountIdSource, String accountIdDestination, double amount)
            throws BalanceNotSufficentException;

    List<BankAccountDTO> bankAccountsList();

    CustomerDTO getCustomer(Long customerId);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long CustomerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size);

    List<CustomerDTO> searchCustomers(String keyword);
}
