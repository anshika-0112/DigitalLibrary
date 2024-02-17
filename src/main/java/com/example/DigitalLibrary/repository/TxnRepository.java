package com.example.DigitalLibrary.repository;

import com.example.DigitalLibrary.model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn,Integer> {
    Txn findByTxnId(String txnId);
}
