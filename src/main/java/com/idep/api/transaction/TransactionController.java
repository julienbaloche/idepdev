package com.idep.api.transaction;

import com.idep.api.common.exceptionhandling.exception.ResourceNotFoundException;
import com.idep.api.object.Object;
import com.idep.api.object.ObjectRepository;
import com.idep.api.transaction.Transaction;
import com.idep.api.transaction.TransactionRepository;
import com.idep.api.user.User;
import com.idep.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final ObjectRepository objectRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository, UserRepository userRepository, ObjectRepository objectRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.objectRepository = objectRepository;
    }


    //GETs

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransaction() {
        List<Transaction> transactions = this.transactionRepository.findAll();
        return transactions;
    }


    @GetMapping("/sold-by")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransactionSoldByUser(@RequestParam("sellerId") long sellerId) {
        User seller = this.userRepository.findById(sellerId).orElseThrow(ResourceNotFoundException::new);
        List<Transaction> transactions = this.transactionRepository.findBySeller(seller);
        return transactions;
    }

    @GetMapping("/borrowed-by")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllTransactionsReceivedByUser(@RequestParam("borrowerId") long borrowerId) {
        User borrower = this.userRepository.findById(borrowerId).orElseThrow(ResourceNotFoundException::new);
        List<Transaction> transactions = this.transactionRepository.findByBorrower(borrower);
        return transactions;
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction getTransactionById(@PathVariable("id") long transactionId) {
        Transaction transaction = this.transactionRepository.findById(transactionId).orElseThrow(ResourceNotFoundException::new);
        return transaction;
    }


    //POSTs

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction addTransaction(@RequestBody Transaction newTransaction,
                                      @RequestParam("objectId") long  objectId,
                                      @RequestParam("sellerId") long sellerId,
                                      @RequestParam("borrowerId") long borrowerId) {

        Object object = this.objectRepository.findById(objectId).orElseThrow(ResourceNotFoundException::new);
        User seller = this.userRepository.findById(sellerId).orElseThrow(ResourceNotFoundException::new);
        User borrower = this.userRepository.findById(borrowerId).orElseThrow(ResourceNotFoundException::new);


        float balanceBeforeTransacSeller = seller.getBalance();
        float balanceBeforeTransacBorrower = borrower.getBalance();
        float transactionAmount = newTransaction.getAmount();

        seller.setBalance(balanceBeforeTransacSeller + transactionAmount);
        borrower.setBalance(balanceBeforeTransacBorrower - transactionAmount);
        newTransaction.setSeller(seller);
        newTransaction.setBorrower(borrower);
        newTransaction.setObjectFromSellerToBorrowerId(object.getId());
        object.setOwner(borrower);


        return this.transactionRepository.save(newTransaction);
    }


    //DELETEs


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction deleteTransactionById(@PathVariable("id") long transactionId) {
        Transaction transactionToDelete = this.transactionRepository.findById(transactionId).orElseThrow(ResourceNotFoundException::new);
        this.transactionRepository.delete(transactionToDelete);
        return transactionToDelete;
    }


}
