package com.digital_banking.bank.entities;

import com.digital_banking.bank.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {// virment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    private String descop;
    // ida mabratch texecuta lik sql command just changi le nom dial un attribut
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @ManyToOne
    @JoinColumn(name = "the_bank_account_id")
    //la case li radi tkoun fiha la clé etangère
    private BankAccount bankAccount;
}
