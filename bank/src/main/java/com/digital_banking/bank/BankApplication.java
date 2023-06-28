package com.digital_banking.bank;

import com.digital_banking.bank.Exception.BalanceNotSufficentException;
import com.digital_banking.bank.dto.BankAccountDTO;
import com.digital_banking.bank.dto.CurrentBankAccountDTO;
import com.digital_banking.bank.dto.CustomerDTO;
import com.digital_banking.bank.dto.SavingBankAccountDTO;
import com.digital_banking.bank.entities.*;
import com.digital_banking.bank.enums.AccountStatus;
import com.digital_banking.bank.enums.OperationType;
import com.digital_banking.bank.mappers.BankAccountMapperImpl;
import com.digital_banking.bank.repository.AccountOperationRepository;
import com.digital_banking.bank.repository.BankAccountRepository;
import com.digital_banking.bank.repository.CustomerRepository;
import com.digital_banking.bank.service.BankAccountService;
import com.digital_banking.bank.service.BankAccountServiceImpl;
import com.digital_banking.bank.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication()
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
    @Bean
		// lambda 5asha point virgule
		//  la partie mn a7san tdir try and catch blast ma tdiclari throws
		// ma5asakch tdir try-catch wast mn try-catch
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService , BankAccountMapperImpl mappeddto){
		return args -> {
			Stream.of("Hassan","Iman","Modamed").forEach(name->{
				Customer customer=new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				CustomerDTO customerDTO =mappeddto.fromCustomer(customer);
				bankAccountService.saveCustomer(customerDTO);
			});
			bankAccountService.listCustomers().forEach(customer -> {
				bankAccountService.saveCurrentBankAccount(Math.random()*9000,9000.00, customer.getId());
				bankAccountService.saveSavingBankAccount(Math.random()*10000,5.5,customer.getId());
				List<BankAccountDTO> bankAccounts=bankAccountService.bankAccountsList();
			  // ayi 7aja katbka localement wast for
				try {
					for (BankAccountDTO bankAccount : bankAccounts) {
						for(int i=0;i<10;i++) {
							String accountid;
							if (bankAccount instanceof SavingBankAccountDTO) {
								accountid = ((SavingBankAccountDTO) bankAccount).getId();
							} else {
								accountid = ((CurrentBankAccountDTO) bankAccount).getId();
							}

							bankAccountService.credit(accountid, 1000 + Math.random(), "credit");
							bankAccountService.debit(accountid, 1000 + Math.random(), "Debit");
						}
					}
				}catch (BalanceNotSufficentException e){
					e.printStackTrace();
				}
				// printStackTrace() c'est une method qui affiche l'exception
				// avec d'autre détails comme le numéro de ligne + la class ou l'exception s'est produite

			});
		};
	}
}
