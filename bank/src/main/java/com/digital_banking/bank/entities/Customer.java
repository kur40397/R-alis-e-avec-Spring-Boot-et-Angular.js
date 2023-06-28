package com.digital_banking.bank.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
// hibernate bach idir 5dmto 5aso default constructor
// bach imappi bin tables b db and entity
@AllArgsConstructor // bach instanciou had l'object
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //hna katgol bli primary key radi tginira b auto-increment
    // database hiya li radi tginiri primarykey
    private  Long id;
    private  String name;
    private  String email;
    // hna bach tstoki une list d'object de type BankAccount
    // mappedBy = "customer" wa7d l'indice li bih katgol m3an radi tkoun la relation  radi treprisentatou par la même clé etranger
    // mappedBy="customer" 3andha a reference 3la l'entite li bara tassocia m3aha
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // had property katmna3 le field li lta7t bach may serializache
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY) bach had lproperty matdeserialisache

    //*******************************************************************************************************************//

    @OneToMany(cascade = CascadeType.REMOVE , orphanRemoval = true,mappedBy = "customer",fetch = FetchType.LAZY) // se traduit par la création d'une clé étrangère
    // katcriyi relation bin deux entites
    // les lists d'instances BankAccount 5asha les tassociaya m3a Customer
    private List<BankAccount> bankAccounts;
    // hna katgirir les object BankAccount
}
