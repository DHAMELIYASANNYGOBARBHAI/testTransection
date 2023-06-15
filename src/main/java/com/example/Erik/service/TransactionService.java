package com.example.Erik.service;

import com.example.Erik.dto.AllItemResponse;
import com.example.Erik.dto.ArticleMasterRequest;
import com.example.Erik.dto.BranchMasterRequest;
import com.example.Erik.dto.ColorMasterRequest;
import com.example.Erik.dto.CompanyLedgerMasterRequest;
import com.example.Erik.dto.DepartmentMasterRequest;
import com.example.Erik.dto.InventoryItemRequest;
import com.example.Erik.dto.TransactionLineItemRequest;
import com.example.Erik.dto.TransactionLineItemResponse;
import com.example.Erik.dto.TransactionRequest;
import com.example.Erik.model.*;
import com.example.Erik.repo.InventoryItemRepo;
import com.example.Erik.repo.TransactionRepo;
import com.example.Erik.repo.TransictionLineItemRepo;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepo transactionRepo;
  @Autowired
  private InventoryItemRepo inventoryItemRepo;
  @Autowired
  private TransictionLineItemRepo transictionLineItemRepo;

  public Transaction createTransaction(TransactionRequest request)
  {
    return transactionRepo.save(convertToTransection(request));
  }

  public TransactionLineItem addLineItem(Long transactionId, TransactionLineItemRequest request) {
    final Transaction transaction = getTransaction(transactionId);
    final TransactionLineItem transactionLineItem = convertToTransectionLineItem(request);
    transactionLineItem.setTransactionId(transactionId);
    final TransactionLineItem actual = transictionLineItemRepo.save(transactionLineItem);
    transaction.getLineItems().add(actual.getUniqueId());
    transactionRepo.save(transaction);
    return actual;
  }

  public InventoryItem addInventoryItem(Long lineItemId, InventoryItemRequest request) {
    final TransactionLineItem lineItem = getLineItem(lineItemId);
    final InventoryItem inventoryItem = converToInventoryItem(request);
    inventoryItem.setTransactionLineItemId(lineItem.getUniqueId());
    final InventoryItem saved = inventoryItemRepo.save(inventoryItem);
    lineItem.getInventoryId().add(saved.getUniqueId());
    return saved;
  }

  public void deleteTransaction(Long transactionId) {
    final Transaction transaction = getTransaction(transactionId);
    final List<Long> lineItemIds = transaction.getLineItems();
    for (Long id : lineItemIds) {
      final TransactionLineItem transactionLineItem = getLineItem(id);
      if (!transactionLineItem.getInventoryId().isEmpty()) {
        transactionRepo.delete(transaction);
      } else {
        throw new IllegalStateException("Cannot delete a transaction with inventory items.");
      }
    }
  }

  public AllItemResponse getAllInventory(Long transactionId) {
    final Transaction transaction = getTransaction(transactionId);
    final AllItemResponse allItemResponse = new AllItemResponse();
    final List<Long> lineItemIds = transaction.getLineItems();
    final List<TransactionLineItemResponse> transactionLineItemResponseList = new ArrayList<>();
    for (Long id : lineItemIds) {
      final TransactionLineItem transactionLineItem = getLineItem(id);
      final TransactionLineItemResponse transactionLineItemResponse = new TransactionLineItemResponse();
      transactionLineItemResponse.setTransactionLineItem(transactionLineItem);
      final List<InventoryItem> inventoryItemList = new ArrayList<>();
      for (Long inventory : transactionLineItem.getInventoryId()) {
        final InventoryItem inventoryItem = inventoryItemRepo.findById(inventory).get();
        inventoryItemList.add(inventoryItem);
      }
      transactionLineItemResponse.setInventoryItemList(inventoryItemList);
      transactionLineItemResponseList.add(transactionLineItemResponse);
    }
    allItemResponse.setTransactionLineItemResponseList(transactionLineItemResponseList);
    return allItemResponse;
  }

  private Transaction getTransaction(Long transactionId) {
    return transactionRepo.findById(transactionId)
    .orElseThrow(() -> new IllegalArgumentException("Invalid transaction ID"));
  }

  private TransactionLineItem getLineItem(Long lineItemId) {
    return transictionLineItemRepo.findById(lineItemId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid lineItem ID"));
  }

  private Transaction convertToTransection(TransactionRequest request) {
    final Transaction transaction = new Transaction();

    transaction.setId(request.getId());
    transaction.setBranch(convertToBranchMaster(request.getBranch()));
    transaction.setTransactionNumber(generateTransactionNumber(request.getId()));
    transaction.setTransactionStatus(TransactionStatus.PENDING);
    transaction.setCompany(convertToLedegeMaster(request.getCompany()));
    transaction.setDepartment(convertToDepartMaster(request.getDepartment()));
    transaction.setRemarks(request.getRemarks());
    transaction.setLineItems(new ArrayList<>());
    return transaction;
  }

  private CompanyLedgerMaster convertToLedegeMaster(CompanyLedgerMasterRequest request) {
    final CompanyLedgerMaster companyLedgerMaster = new CompanyLedgerMaster();
    companyLedgerMaster.setName(request.getName());
    return companyLedgerMaster;
  }

  private ArticleMaster convertToArticaleMaster(ArticleMasterRequest request) {
    final ArticleMaster articleMaster = new ArticleMaster();
    articleMaster.setName(request.getName());
    return articleMaster;
  }

  private BranchMaster convertToBranchMaster(BranchMasterRequest request) {
    final BranchMaster branchMaster = new BranchMaster();
    branchMaster.setName(request.getName());
    return branchMaster;
  }

  private ColorMaster convertToColorMaster(ColorMasterRequest request) {
    final ColorMaster colorMaster = new ColorMaster();
    colorMaster.setName(request.getName());
    return colorMaster;
  }

  private DepartmentMaster convertToDepartMaster(DepartmentMasterRequest request) {
    final DepartmentMaster departmentMaster = new DepartmentMaster();
    departmentMaster.setName(request.getName());
    return departmentMaster;
  }

  private TransactionLineItem convertToTransectionLineItem(TransactionLineItemRequest request) {
    final TransactionLineItem transactionLineItem = new TransactionLineItem();
    transactionLineItem.setArticle(convertToArticaleMaster(request.getArticle()));
    transactionLineItem.setColour(convertToColorMaster(request.getColour()));
    transactionLineItem.setQuantity(request.getQuantity());
    transactionLineItem.setUnit(request.getUnit());
    transactionLineItem.setRatePerUnit(request.getRatePerUnit());
    transactionLineItem.setInventoryId(new ArrayList<>());
    return transactionLineItem;
  }

  private String generateTransactionNumber(Long id) {
    String year = String.valueOf(Year.now().getValue());
    int count = 1;
    return "TRN/" + id + "/" + year;
  }

  private InventoryItem converToInventoryItem(InventoryItemRequest request) {
    final InventoryItem inventoryItem = new InventoryItem();
    inventoryItem.setArticle(convertToArticaleMaster(request.getArticle()));
    inventoryItem.setColour(convertToColorMaster(request.getColour()));
    inventoryItem.setCompany(convertToLedegeMaster(request.getCompany()));
    inventoryItem.setGrossQuantity(request.getGrossQuantity());
    inventoryItem.setUnit(request.getUnit());
    inventoryItem.setNetQuantity(request.getNetQuantity());
    return inventoryItem;
  }
}
