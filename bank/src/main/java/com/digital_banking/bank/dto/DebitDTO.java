package com.digital_banking.bank.dto;

import lombok.Data;

@Data
public class DebitDTO {
    private String Id;
    private double amount;
    private String description;
}
