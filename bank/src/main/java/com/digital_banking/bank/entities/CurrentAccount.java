package com.digital_banking.bank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@DiscriminatorValue("CA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccount extends BankAccount{
    private double overDraft;


    // overDraft hiya mnin la bank katwali tsalek mni katlob ktar mn li 3andek
}
