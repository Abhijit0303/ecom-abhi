package com.abhijit.ecomabhi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Catagory Name can't be blank")
    @Size(min = 5 ,message = "Catagory Name mustn't be less than 5 charecters")
    private String CatagoryName;
}
