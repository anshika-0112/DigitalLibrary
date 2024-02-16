package com.example.DigitalLibrary.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length=30, unique = true)
    private String email;

    @Column(name="phoneNo",length = 10,unique = true,nullable = false)
    private String phoneNo;

    private String address;

    @Enumerated(value=EnumType.STRING)
    private StudentType status;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    List<Book> list;

    @OneToMany(mappedBy = "student")
    List<Txn> listTxn;
}
