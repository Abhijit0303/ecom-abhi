package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.payload.CatagoryDTO;
import com.abhijit.ecomabhi.payload.CatagoryResponse;

import java.util.List;

public interface CatagoryService {
    CatagoryResponse getAllCatagories();
    CatagoryDTO createCatagory(CatagoryDTO catagoryDTO);

   String deleteCatagory(Long catagoryId);

    Catagories updateCatagory(Long catagoryId, Catagories catagories);
}
