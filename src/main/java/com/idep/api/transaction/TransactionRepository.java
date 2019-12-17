package com.idep.api.transaction;

import java.util.List;

import com.idep.api.user.User;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    List<Transaction> findAll();
    List<Transaction> findBySeller(User seller);
    List<Transaction> findByBorrower(User borrower);
}