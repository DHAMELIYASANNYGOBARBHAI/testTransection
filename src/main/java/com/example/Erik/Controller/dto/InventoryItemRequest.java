package com.example.Erik.Controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryItemRequest {

  private ArticleMasterRequest article;
  private ColorMasterRequest colour;
  private CompanyLedgerMasterRequest company;
  private double grossQuantity;
  private double netQuantity;
  private String unit;
}
