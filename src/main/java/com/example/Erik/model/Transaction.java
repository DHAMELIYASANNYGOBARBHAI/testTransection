package com.example.Erik.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Year;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyLedgerMaster company;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private BranchMaster branch;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentMaster department;


    private String transactionNumber;

    private String transactionStatus;

    private String remarks;


//    @PrePersist
//    private void generateTransactionNumber() {
//        String year = String.valueOf(Year.now().getValue());
//        // Implement logic to fetch and reset count for each year from your repository
//        int count = 1; // Assume count is fetched/reset to 1 for simplicity
//        this.transactionNumber = "TRN/" + count + "/" + year;
//    }


}
