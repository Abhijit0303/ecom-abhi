package com.abhijit.ecomabhi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "catagories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catagories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CatagoryId;
    private String CatagoryName;
}
