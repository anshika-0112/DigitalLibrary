package com.example.DigitalLibrary.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TxnCreateRequest {

    private String studentContact;
    private String bookNo;

    private int paidAmount;

}
