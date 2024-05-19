package com.lemmyc.em_app.controller;

import com.lemmyc.em_app.model.Transaction;
import com.lemmyc.em_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

//    @GetMapping("/transactions")
//    public String showTransactionsPage(Model model) {
//        model.addAttribute("transactions", transactionService.getTransactionByUserId(1L));
//        return "transactions";
//    }
//
//    @GetMapping("/report")
//    public String showReportPage() {
//        return "report";
//    }
}
