package com.digital_banking.bank.dto;

import com.digital_banking.bank.entities.Customer;
import com.digital_banking.bank.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

// l'entity 5as ikoun m3aha l'id
// une entity katheriti mn une autre entity 5asak tdirlih dto 
@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
