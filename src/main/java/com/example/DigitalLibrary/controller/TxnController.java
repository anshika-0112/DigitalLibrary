package com.example.DigitalLibrary.controller;

import com.example.DigitalLibrary.exception.TxnException;
import com.example.DigitalLibrary.request.TxnCreateRequest;
import com.example.DigitalLibrary.request.TxnReturnRequest;
import com.example.DigitalLibrary.response.GenericResponse;
import com.example.DigitalLibrary.service.TxnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TxnController {

    @Autowired
    private TxnService txnService;
    @PostMapping("/create")
    public ResponseEntity<GenericResponse<String>> createTransaction(@RequestBody @Valid TxnCreateRequest txnCreateRequest) throws TxnException {
        String txnId= txnService.createTransaction(txnCreateRequest);
        GenericResponse<String> response=new GenericResponse<>(txnId,"","success","200");
        ResponseEntity responseEntity=new ResponseEntity<>(response,HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/returnBook")
    public int returnBook(@RequestBody TxnReturnRequest txnReturnRequest) throws TxnException {
        return txnService.returnBook(txnReturnRequest);
    }
}
