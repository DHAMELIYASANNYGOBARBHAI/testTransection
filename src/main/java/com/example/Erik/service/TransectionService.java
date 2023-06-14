package com.example.Erik.service;

import com.example.Erik.model.Transaction;
import com.example.Erik.repo.TransectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransectionService {


    @Autowired
    TransectionRepo transectionRepo;

    public Transaction addTransction(Transaction transaction) {

         return transectionRepo.save(transaction);
    }
}
