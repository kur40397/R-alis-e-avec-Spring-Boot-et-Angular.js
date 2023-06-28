package com.digital_banking.bank.dto;

import lombok.Data;

import java.util.List;
@Data
// imkanlik tdir fiha les paginations
public class AccountHistoryDTO {
    private String accountId;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<AccountOperationDTO> accountOperationDTOs;
}
