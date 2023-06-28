package com.digital_banking.bank.repository;

import com.digital_banking.bank.entities.AccountOperation;
import com.digital_banking.bank.entities.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    public List<AccountOperation> findByBankAccountId(String accountId);
    // les methods dial la pagination kaynin f reposirtory
    // Pageable wa7d lmethod li kat5alina naffichiou data dialna sous fourm de page kaykbal numero dial la page courrant & size
    Page<AccountOperation> findByBankAccountIdOrderByOperationDateDesc(String accountId, Pageable pageable);
    // imkan liya ndir OrderByOperationDate
    //

}
