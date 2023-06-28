package com.digital_banking.bank.web;

import com.digital_banking.bank.Exception.BalanceNotSufficentException;
import com.digital_banking.bank.dto.*;
import com.digital_banking.bank.service.BankAccountService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    // @PathVariable extract dynamique value from the UR
    public BankAccountDTO getBankAccount(@PathVariable String accountId) {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts() {
        return bankAccountService.bankAccountsList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
      return bankAccountService.accountHistory(accountId);
    }
    // f getMapping maymkanch lik tdir les requestparam 5asak tdir rir PathVariable
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name = "page",defaultValue = "0") int page,
                                               @RequestParam(name="size",defaultValue = "0") int size){
        return bankAccountService.getAccountHistory(accountId,page,size);
    }
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BalanceNotSufficentException {
        System.out.println(debitDTO.getId()+ "ddd");
        this.bankAccountService.debit(debitDTO.getId(),debitDTO.getAmount(),debitDTO.getDescription());
       return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BalanceNotSufficentException {
         this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BalanceNotSufficentException {
        System.out.println(transferRequestDTO.getAccountSrc());
        System.out.println(transferRequestDTO.getAccountDet()+"dest");
        this.bankAccountService.transfer(transferRequestDTO.getAccountSrc(),
                transferRequestDTO.getAccountDet(),
                transferRequestDTO.getAmount());

    }
}
