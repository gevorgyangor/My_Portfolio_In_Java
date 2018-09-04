package com.example.demo.model;

import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "repeat_password")
    private String repeatPassword;
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType type;
    @Column
    private boolean verify;
    @Column
    private String token;

}
