package com.example.Erik.dto;

import com.example.Erik.model.InventoryItem;
import com.example.Erik.model.TransactionLineItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionLineItemResponse {

  private TransactionLineItem transactionLineItem;
  private List<InventoryItem> inventoryItemList;
}
