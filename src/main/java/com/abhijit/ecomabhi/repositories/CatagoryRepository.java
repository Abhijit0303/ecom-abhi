package com.abhijit.ecomabhi.repositories;

import com.abhijit.ecomabhi.model.Catagories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatagoryRepository extends JpaRepository<Catagories, Long> {
    Catagories findByCatagoryName(@NotBlank(message = "Catagory Name can't be blank") @Size(min = 5 ,message = "Catagory Name mustn't be less than 5 charecters") String catagoryName);
}
