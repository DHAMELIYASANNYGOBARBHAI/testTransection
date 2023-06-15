package com.example.Erik.repo;

import com.example.Erik.model.person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface personrepo extends MongoRepository<person,Integer> {

}
