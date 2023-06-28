package com.digital_banking.bank.dto;

import lombok.Data;

@Data
public class TransferRequestDTO {
   private String accountSrc;
   private String accountDet;
   private  double amount;
   private  String description;
}
