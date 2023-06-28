package com.digital_banking.bank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@DiscriminatorValue("SA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccount extends BankAccount{
    private double interestRate;
    // The customer benefits from earning interest on their savings,
    // which helps their funds grow over time
    //taux d'intérer
}
