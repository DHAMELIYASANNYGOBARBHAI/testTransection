package com.example.Erik.repo;

import com.example.Erik.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransectionRepo extends JpaRepository<Transaction,Long> {


}
