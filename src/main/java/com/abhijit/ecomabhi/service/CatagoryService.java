package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.payload.CatagoryDTO;
import com.abhijit.ecomabhi.payload.CatagoryResponse;

import java.util.List;

public interface CatagoryService {
    CatagoryResponse getAllCatagories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CatagoryDTO createCatagory(CatagoryDTO catagoryDTO);

    CatagoryDTO deleteCatagory(Long catagoryId);

    CatagoryDTO updateCatagory(Long catagoryId, CatagoryDTO catagoryDTO);
}
