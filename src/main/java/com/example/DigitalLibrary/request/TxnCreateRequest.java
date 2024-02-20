package com.example.DigitalLibrary.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TxnCreateRequest {
    @NotBlank(message = "Student contact cannot be blank")
    private String studentContact;
    @NotBlank(message = "Book number cannot be blank")
    private String bookNo;
    private int paidAmount;

}
