package com.lemmyc.em_app.service;


import com.lemmyc.em_app.model.Transaction;
import com.lemmyc.em_app.model.User;
import com.lemmyc.em_app.repository.TransactionRepository;
import com.lemmyc.em_app.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    public void saveTransaction(String token, Transaction transaction){
        token = token.substring(7);
        String username = jwtService.extractUsername(token);
        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty()){
            return;
        }
        transaction.setUser(user.get());
        transactionRepository.save(transaction);
        return;
    }

    public List<Transaction> getTransactionByUserId(Long userId){
        return transactionRepository.findByUserId(userId);
    }

}
