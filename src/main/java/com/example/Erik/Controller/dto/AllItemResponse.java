package com.example.Erik.Controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllItemResponse {

  private List<TransactionLineItemResponse> transactionLineItemResponseList;
}
