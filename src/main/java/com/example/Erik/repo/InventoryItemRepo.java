package com.example.Erik.repo;

import com.example.Erik.model.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepo extends MongoRepository<InventoryItem, Long> {

}
