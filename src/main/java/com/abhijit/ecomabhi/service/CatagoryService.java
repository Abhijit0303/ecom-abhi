package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.model.Catagories;

import java.util.List;

public interface CatagoryService {
    List<Catagories> getAllCatagories();
    void createCatagory(Catagories catagories);

   String deleteCatagory(Long catagoryId);

    Catagories updateCatagory(Long catagoryId, Catagories catagories);
}
