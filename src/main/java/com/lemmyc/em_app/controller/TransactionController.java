package com.lemmyc.em_app.controller;


import com.lemmyc.em_app.model.Transaction;
import com.lemmyc.em_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Long userId){
        return transactionService.getTransactionByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestHeader(name="Authorization") String token ,@RequestBody Transaction transaction){
        transactionService.saveTransaction(token, transaction);
        return ResponseEntity.ok("Transaction added successfully");
    }

}
