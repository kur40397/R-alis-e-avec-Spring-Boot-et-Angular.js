package com.digital_banking.bank.web;

import com.digital_banking.bank.dto.CustomerDTO;
import com.digital_banking.bank.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
// requestmap imkan ndirouha f bra lcontroller wla lda5l
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customer")
    public List<CustomerDTO>  customers(){
        return bankAccountService.listCustomers();
    }
    @GetMapping("/customer/search")
    public List<CustomerDTO>  searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return bankAccountService.searchCustomers("%"+keyword+"%");
        // la recherche generique katchouf wach les noms dial les customers kaymatichiou l keyword
        // la recherche generique , katchouf wach le nom dial customer contient lkeyword

    }
    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId){
       return bankAccountService.getCustomer(customerId);
    }
    @PostMapping("/addcustomer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
      return bankAccountService.saveCustomer(customerDTO);
    }
    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
     bankAccountService.deleteCustomer(id);
    }

}
