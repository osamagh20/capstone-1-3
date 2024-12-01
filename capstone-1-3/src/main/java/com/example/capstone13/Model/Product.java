package com.example.capstone13.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Enter the name")
    @Size(min = 4,message = "Enter the name with 3 characters or above")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @Positive
    @Column(columnDefinition = "double not null")
    private Double price;
    @NotNull(message = "Enter the category id")
    @Column(columnDefinition = "int not null")
    private Integer category_id;
    @Column(columnDefinition = "varchar(200)")
    private String comments;
}
