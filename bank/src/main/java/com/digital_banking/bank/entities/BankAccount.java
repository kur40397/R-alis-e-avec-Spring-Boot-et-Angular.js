package com.digital_banking.bank.entities;

import com.digital_banking.bank.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity // biha kanreprisentiou had la class f la table
// In table-per-class strategy, for each sub entity class a separate table is generated et fiha les attributs kamline et jointure makaynach
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // had single table katsma bsmit table mère
@DiscriminatorColumn(name = "TYPE",length = 4 )
@Data
@NoArgsConstructor
@AllArgsConstructor
public  abstract   class BankAccount { // mn a7san ida kan 3andek l'heritage

    // tkad tkoun 3andha une method abtraite wla matkounch
    // drna abstruct bach la table BankAccount matgenerach
    // radi ikriyi rir les tables bnisba les class dérivée
    //*****************************************************//
    //Inheritance: Abstract classes serve as base classes that
    // can be extended by subclasses
    @Id
    //TABLE_PER_CLASS 5asak tdir nasf strategy
    // kaygolik mn a7san tdir nasf strategy all the table
    //GenerationType.IDENTITY katdar ida kan3andak rir une seule table

    private String id; // la table bank_account_seq katginiri lik l'id
    // ida knti bari tdir 1,2,3 5asak tdir f type Integer
    // @GeneratedValue(strategy = GenerationType.TABLE) kidriha ida makantch 3andak auto-incremented
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    //**************************************************//
    //bidirectional relationship (@OneToMany or @ManyToOne)
    @ManyToOne

    private Customer customer;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true,mappedBy = "bankAccount",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // had property katmna3 le field li lta7t bach may serializache

    //fetch = FetchType.LAZY f la partie objet

    // mappedBy un indice kayindiki m3amn radi ndirou la relation
    // Lazy : va charger les données a la demande
    // EAGER: va le charger peut import vous la demander ou non
    private List<AccountOperation> accountOperationList; // les operations dial debit et crédit
    // hold les object AccountOperationli associer m3a BankAccount
    // zdna une list accountOperationList kola instance BankAccount kadin insiriou fiha une list dial les accountOperationList
}
