package com.example.capstone13.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Enter the name")
    @Size(min = 10,message = "Enter the name with 10 characters or above")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;
    @NotEmpty(message = "Enter the password")
    @Size(min = 6,message = "Enter password 6 characters or above ")
    @Pattern(regexp = "^[0-9a-zA-Z]+$",message = "Enter with digits and characters only.")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;
    @NotEmpty(message = "Enter the email")
    @Email
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;
    @NotEmpty(message = "Enter the Role")
    @Pattern(regexp = "^(Admin|Customer)$",message = "Please chose Admin or Customer")
    @Column(columnDefinition = "varchar(15) not null")
    private String role;
    @NotNull(message = "Enter the balance")
    @Positive
    @Column(columnDefinition = "double not null")
    private Double balance;
    @Column(columnDefinition = "int")
    private Integer item;
    @Column(columnDefinition = "int")
    private Integer visiting;
    @NotEmpty(message = "Enter your home")
    @Column(columnDefinition = "varchar(50) not null")
    private String homeAddress;
    private String subscribe;





}
