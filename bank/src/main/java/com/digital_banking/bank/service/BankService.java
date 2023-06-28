package com.digital_banking.bank.service;

import com.digital_banking.bank.entities.BankAccount;
import com.digital_banking.bank.entities.CurrentAccount;
import com.digital_banking.bank.entities.SavingAccount;
import com.digital_banking.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // drnaha bach t5dam lina Lazy
// ida barina nchargiou les operations et 3andna lazy donc 5asna ndirou Transactional
// avec transactional katzid addtional queries  bach t3awan spring bach ifitichi
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("1").orElse(null);
        // katjib lina les objets mn la table BankAccount li majmou3in fiha les deux tables dérivée
        // findById method returns an Optional
        // Optional is like a special box in Java that can either contain something or be empty
        // Instead of using null to represent the absence of a value, Optional
        // allows you to express that something may or may not be present in a clearer and safer way.
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getStatus());
        System.out.println(bankAccount.getCreatedAt());
        System.out.println(bankAccount.getCustomer().getName());
        if(bankAccount instanceof CurrentAccount){
            // tkagol wach had l'objet wach type dialo CurrentAccount wla Saving account
            // wach imkan lina nsta3mlou les methods dial CurrentAccount
            // katchof wach bankAccount une instance tkriyat b CurrentAccount
            // bach  t3raf ina class minstanci bih had l'object
            System.out.println(((CurrentAccount)bankAccount).getOverDraft());
            // katgol lcompilateur to treat bankAccount as CurrentAccount
        }else {
            System.out.println(((SavingAccount)bankAccount).getInterestRate());
        }
        bankAccount.getAccountOperationList().forEach(op->{
            System.out.println(op.getOperationType()+ " "+
                    op.getOperationDate());
        });

    }
}
