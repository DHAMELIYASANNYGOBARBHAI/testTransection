package com.example.Erik.repo;

import com.example.Erik.model.TransactionLineItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransictionLineItemRepo extends MongoRepository<TransactionLineItem, Long> {

}
