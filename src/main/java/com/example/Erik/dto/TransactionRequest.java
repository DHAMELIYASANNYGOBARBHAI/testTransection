package com.example.Erik.dto;


import com.example.Erik.model.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRequest {

  private Long id;
  private CompanyLedgerMasterRequest company;
  private BranchMasterRequest branch;
  private DepartmentMasterRequest department;
  private TransactionStatus transactionStatus;
  private String remarks;

//    @PrePersist
//    private void generateTransactionNumber() {
//        String year = String.valueOf(Year.now().getValue());
//        // Implement logic to fetch and reset count for each year from your repository
//        int count = 1; // Assume count is fetched/reset to 1 for simplicity
//        this.transactionNumber = "TRN/" + count + "/" + year;
//    }


}
