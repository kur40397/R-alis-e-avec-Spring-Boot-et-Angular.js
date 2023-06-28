package com.digital_banking.bank.service;

import com.digital_banking.bank.Exception.BalanceNotSufficentException;
import com.digital_banking.bank.Exception.BankAccountNotFoundException;
import com.digital_banking.bank.Exception.CustomerNotFoundException;
import com.digital_banking.bank.dto.*;
import com.digital_banking.bank.entities.*;
import com.digital_banking.bank.enums.OperationType;
import com.digital_banking.bank.mappers.BankAccountMapperImpl;
import com.digital_banking.bank.repository.AccountOperationRepository;
import com.digital_banking.bank.repository.BankAccountRepository;
import com.digital_banking.bank.repository.CustomerRepository;
//java.util.logging.Logger
//import org.slf4j.LoggerFactory;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service // l'implementation hiya li radin ndirouliha un service
@Transactional // It ensures that all database operations within the annotated service methods
               // either succeed as a whole or fail completely
@Slf4j
// logging framework
// mnin katdirha automaticement le compilateur kayginiri logger object named log
// 5sak timplementi les methods kamkin

public class BankAccountServiceImpl implements BankAccountService {
    /*
     * A a= new B() hna katcriyi instance of class B et type dialha A
     * a imkan liha taxidi les methods dela class A et ayi overriden
     * additional members defined in class B imkan lik takidi liha ida kan3adenk la
     * variable de type A
     * To access overridden or additional members of class B, you can do:
     * //B b = (B) a;
     */
    private CustomerRepository customerRepository; // hna katdeclari les dependances

    private BankAccountRepository bankAccountRepository;

    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImpl dtomapper;
    //
    // Logger log = Logger.getLogger(this.getClass().getName());
    // log wa7d l'objet pour log notre application
    // kandirou le nom de la class bach n3arfo ina partie mn le programme had
    // messagate jayine
    // bach n3arfo mnin had les log messagace jaynin // 7na li kay3awana

    // les dependances kaytajictaou f le constructeur , et kbal hadchi tkriyi les
    // instances
    // hna hadouk les dependances li mdeclariin kaunkyi lihoum l'instance et
    // kan7atoum f constructor
    public BankAccountServiceImpl(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
            AccountOperationRepository accountOperationRepository, BankAccountMapperImpl dtomapper) {
        this.customerRepository = customerRepository;
        // hada rir variable instance kataffici l'instance customerRepository bach mn
        // ba3d nsta3mlou les methods dial customerRepository
        //
        this.bankAccountRepository = bankAccountRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.dtomapper = dtomapper;
    }
    // mnin katinjicti imkan lina nsta3mlou customerRepository

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        Customer customer = dtomapper.fromCustomerDTO(customerDTO);
        Customer saveCustomer = customerRepository.save(customer);
        return dtomapper.fromCustomer(saveCustomer);
    }

    @Override
    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId)
            throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) { // dayman 5sak tverifi
            throw new CustomerNotFoundException("d ");
        }

        // polymorphisme => la creation dial un objet from child class and using a
        // variable of parent class
        // type BankAccount katgol hna z3ma ana had l'objet imkan ikounn parent wla
        // child
        // option bach n5afo 3lina lcode bach man nkriyiou objet lkola sub class
        // kandirou lpolymorphisme
        // 5as b 'une seule class
        CurrentAccount currentAccount = new CurrentAccount();
        Random random = new Random();
        currentAccount.setId(UUID.randomUUID().toString()); // kayginiri liya un numero mn 0 l 49

        // (int) kay7yad lik la partie decimal et kay5ali la partie positive

        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);
        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);
        return dtomapper.fromCurrentBankAccount(savedBankAccount);
    }

    @Override
    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId)
            throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null)// dayman 5sak tverifi
            throw new CustomerNotFoundException("d ");

        // polymorphisme => la creation dial un objet from child class and using a
        // variable of parent class
        // type BankAccount katgol hna z3ma ana had l'objet imkan ikounn parent wla
        // child
        // option bach n5afo 3lina lcode bach man nkriyiou objet lkola sub class
        // kandirou lpolymorphisme
        // 5as b 'une seule class
        SavingAccount savingAccount = new SavingAccount();

        savingAccount.setId(UUID.randomUUID().toString()); // kayginiri liya un numero mn 0 l 49

        // (int) kay7yad lik la partie decimal et kay5ali la partie positive

        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);
        SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
        return dtomapper.fromSavingBankAccount(savedBankAccount);
    }

    @Override
    public List<CustomerDTO> listCustomers() {

        List<Customer> customers = customerRepository.findAll();
        // la programmation impératif ==> classic , fih détail , un programme fih detail
        // la programmation fonctionnel ==> fin katsta3mal streams , fin katsta3mal les
        // fonctions , les fonctions massocine
        List<CustomerDTO> custDTO = customers.stream().map(cust -> dtomapper.fromCustomer(cust))
                .collect(Collectors.toList());
        return custDTO;
    }

    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank not found"));
        // blast mat dir condition dir orElseThrow kadir mast mnha lambda
        // dayman bankAccountRepo dir tchiki wach null 3ad dir had l'operation
        // wa5a machi darouri tdir throws f runtimeexception walakin 5sak tdrha
        if (bankAccount instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return dtomapper.fromSavingBankAccount(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return dtomapper.fromCurrentBankAccount(currentAccount);
        }
    }
    // les regles de metier ; homa les tests li kandirou if + exception

    @Override
    // 5araj flosak mn l7sab
    public void debit(String accountId, double amount, String myDesc) throws BalanceNotSufficentException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank not found"));
        System.out.println(bankAccount);
        // balance ==> solde
        // amount ==> montant
        if (bankAccount.getBalance() < amount)
            throw new BalanceNotSufficentException("Balance not suffisient");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescop(myDesc);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);// bima anaka criyi l'operation 5asak tgol ina Account taba3 lih
       accountOperationRepository.save(accountOperation);
        // 5ona dar l'operation debit
        // kola operation ketatar 3la Account f la partie balance
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    // ida 3andek un type de retour 7awal tdir dto
    @Override
    // katzid lfols f compte
    public void credit(String accountId, double amount, String myDesc) throws BalanceNotSufficentException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank not found"));
        // balance ==> solde
        // amount ==> montant
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescop(myDesc);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);// bima anaka criyi l'operation 5asak tgol ina Account taba3 lih
        AccountOperation bank=accountOperationRepository.save(accountOperation);
        // 5ona dar l'operation debit
        // kola operation ketatar 3la Account f la partie balance
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount)
            throws BalanceNotSufficentException {
        debit(accountIdSource, amount, "Transfer to" + accountIdDestination); // had la method radi throwi l'exception
                                                                              // fiha exception donc 5asha declaration
        credit(accountIdDestination, amount, "Transfer to" + accountIdSource);

    }

    // f une list kola objet n7wlouh ldto
    @Override
    public List<BankAccountDTO> bankAccountsList() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        // une list drti liha lmap
        // mn ba3d 5asak tdir une list jdida
        List<BankAccountDTO> bankAccountsDTO = bankAccounts.stream().map(account -> {
            if (account instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) account;
                return dtomapper.fromSavingBankAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) account;
                return dtomapper.fromCurrentBankAccount(currentAccount);
            }

        }).collect(Collectors.toList());
        return bankAccountsDTO;
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) {
        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return dtomapper.fromCustomer(customer);
    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        Customer customer = dtomapper.fromCustomerDTO(customerDTO);
        Customer saveCustomer = customerRepository.save(customer);
        return dtomapper.fromCustomer(saveCustomer);
    }

    @Override
    public void deleteCustomer(Long CustomerId) {
        customerRepository.deleteById(CustomerId);
    }
    @Override
    public  List<AccountOperationDTO> accountHistory(String accountId){
        // intilakan mn account radi naffichiou une list dial les operations

       List<AccountOperation> accountOperations= accountOperationRepository.findByBankAccountId(accountId);
       // accountOperation radi t7awlou l accountOperationDTO
       return accountOperations.stream().map(op->dtomapper.fromAccountOperation(op)).collect(Collectors.toList());

    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElse(null);
        if(bankAccount==null) throw new BankAccountNotFoundException("Account not Found");
        Page<AccountOperation> accountOperations= accountOperationRepository.findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page,size));
        AccountHistoryDTO accountHistoryDTO= new AccountHistoryDTO();
        List<AccountOperationDTO> accountOperationDTOS= accountOperations.getContent().stream().map(op->dtomapper.fromAccountOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOs(accountOperationDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers=customerRepository.searchCustomer(keyword);
        List<CustomerDTO> customerDTOS= customers.stream().map(cust ->dtomapper.fromCustomer(cust)).collect(Collectors.toList());
        return customerDTOS;
    }
}
