package com.digital_banking.bank.dto;

import com.digital_banking.bank.entities.BankAccount;
import com.digital_banking.bank.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
// les relations onetomany manytoone ou dakchi 7ayad
@Data

public class AccountOperationDTO {// virment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    private String descop;
    // ida mabratch texecuta lik sql command just changi le nom dial un attribut
    private OperationType operationType;
}
