package com.digital_banking.bank.dto;

import com.digital_banking.bank.entities.Customer;
import com.digital_banking.bank.enums.AccountStatus;
import lombok.Data;
import java.util.Date;

// l'entity 5as ikoun m3aha l'id
// une classe wa5a tkoun de type entity wla 3adi 
// darouri les getter et les setter
@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}
