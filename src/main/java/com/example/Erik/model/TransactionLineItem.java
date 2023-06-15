package com.example.Erik.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class TransactionLineItem {

  @Id
  private Long uniqueId;
  private ArticleMaster article;
  private ColorMaster colour;
  private Date requiredOnDate;
  private double quantity;
  private int ratePerUnit;
  private String unit;
  private Long transactionId;
  private List<Long> inventoryId;
}
