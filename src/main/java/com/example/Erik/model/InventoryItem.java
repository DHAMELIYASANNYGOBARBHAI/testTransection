package com.example.Erik.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uniqueId;
  private ArticleMaster article;
  private ColorMaster colour;
  private CompanyLedgerMaster company;
  private double grossQuantity;
  private double netQuantity;
  private String unit;
  private Long transactionLineItemId;
}
