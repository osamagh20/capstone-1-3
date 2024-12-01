package com.example.capstone13.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer merchant_id;
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer product_id;
    @NotNull(message = "Enter the stock")
    @Positive
    @Min(10)
    @Column(columnDefinition = "int not null")
    private Integer stock;
}
