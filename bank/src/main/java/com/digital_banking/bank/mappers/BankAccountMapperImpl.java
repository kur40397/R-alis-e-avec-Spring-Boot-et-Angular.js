package com.digital_banking.bank.mappers;

import com.digital_banking.bank.dto.AccountOperationDTO;
import com.digital_banking.bank.dto.CurrentBankAccountDTO;
import com.digital_banking.bank.dto.CustomerDTO;
import com.digital_banking.bank.dto.SavingBankAccountDTO;
import com.digital_banking.bank.entities.AccountOperation;
import com.digital_banking.bank.entities.CurrentAccount;
import com.digital_banking.bank.entities.Customer;
import com.digital_banking.bank.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
// f component service fin katdir mapping
// fin katdir les calcules
public class BankAccountMapperImpl {
    // sayab lina customerDTO
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO =new CustomerDTO();
        // copy property value mn customer customerDTO
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
    //MapStruct
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }
    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }
    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
      SavingAccount savingAccount = new SavingAccount();
      BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
      savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
      return savingAccount;
    }
    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
      CurrentBankAccountDTO  currentBankAccountDTO=new CurrentBankAccountDTO();
      BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
      currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
      currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
      return currentBankAccountDTO;
    }
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
      CurrentAccount currentAccount=new CurrentAccount();
      BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
      currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
      return currentAccount;
    }
    public AccountOperationDTO fromAccountOperation(AccountOperation accountoperation){
        // la creation de l'objet dto
        AccountOperationDTO accountOperationDTO= new AccountOperationDTO();
        BeanUtils.copyProperties(accountoperation,accountOperationDTO);
        return accountOperationDTO;
    }
}
