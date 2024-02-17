package com.example.DigitalLibrary.controller;

import com.example.DigitalLibrary.exception.TxnException;
import com.example.DigitalLibrary.request.TxnCreateRequest;
import com.example.DigitalLibrary.request.TxnReturnRequest;
import com.example.DigitalLibrary.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TxnController {

    @Autowired
    private TxnService txnService;
    @PostMapping("/create")
    public String createTransaction(@RequestBody TxnCreateRequest txnCreateRequest) throws TxnException {
        return txnService.createTransaction(txnCreateRequest);
    }

    @PutMapping("/returnBook")
    public int returnBook(@RequestBody TxnReturnRequest txnReturnRequest) throws Exception {
        return txnService.returnBook(txnReturnRequest);
    }
}
