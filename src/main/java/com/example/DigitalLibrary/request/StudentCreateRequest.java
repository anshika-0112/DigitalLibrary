package com.example.DigitalLibrary.request;

import com.example.DigitalLibrary.model.Student;
import com.example.DigitalLibrary.model.StudentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {
    private String name;
    private String email;
    private String phoneNo;

    private String address;
    private StudentType status;

    public Student toStudent(){
        return Student.builder().
                name(this.name).
                email(this.email).
                phoneNo(this.phoneNo).
                address(this.address).
                status(this.status).
                build();
    }
}
