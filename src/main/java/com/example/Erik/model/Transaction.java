package com.example.Erik.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Transaction {

  @Id
  private Long id;
  private CompanyLedgerMaster company;
  private BranchMaster branch;
  private DepartmentMaster department;
  private String transactionNumber;
  private TransactionStatus transactionStatus;
  private String remarks;
  private List<Long> lineItems;
}
