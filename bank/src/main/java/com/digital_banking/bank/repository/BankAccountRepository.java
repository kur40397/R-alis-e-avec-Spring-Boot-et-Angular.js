package com.digital_banking.bank.repository;

import com.digital_banking.bank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//***********************************************************//
// ida kan l'heritage peu import strategy
// f une class katheriti mn une autre classe
// one reposiroty kafi ida kanou 3andna nafs les methods crud
// ida knti bari tzid des method m3adak dir seperate repository
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    // BankAccount howa l'input and l'output dial ayi object



}
// JOIN strategy hiya wa7ida li fiha la jointure et toute les classes
// radin n5dmo fihoum

// bank_account_seq refers to a sequence generator used in a database
// to generate unique sequential numbers for bank account id

