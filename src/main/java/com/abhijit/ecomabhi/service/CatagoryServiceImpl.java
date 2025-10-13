package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.exceptions.APIException;
import com.abhijit.ecomabhi.exceptions.ResourceNotFoundException;
import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.repositories.CatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatagoryServiceImpl implements CatagoryService {

    @Autowired
    private CatagoryRepository catagoryRepository;

    @Override
    public List<Catagories> getAllCatagories() {
        List<Catagories> catagories = catagoryRepository.findAll();
        if (catagories.isEmpty()) {
            throw new APIException("No catagory created till now");
        }
        return catagories;
    }

    @Override
    public void createCatagory(Catagories catagories) {
//
        Catagories savedCatagory = catagoryRepository.findByCatagoryName(catagories.getCatagoryName());
        if  (savedCatagory != null) {
            throw new APIException("Catagory with the name " + catagories.getCatagoryName() + " already exists !!!");
        }
        catagoryRepository.save(catagories);
    }

    @Override
    public String deleteCatagory(Long catagoryId) {

        Catagories catagory = catagoryRepository.findById(catagoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Catagory", "catagoryId", catagoryId)
                );

        catagoryRepository.delete(catagory);

        return "Catagory with ID " + catagoryId + " Deleted Successfully";

    }

    @Override
    public Catagories updateCatagory(Long catagoryId, Catagories catagories) {
        Catagories savedCatagory = catagoryRepository.findById(catagoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Catagory", "catagoryId", catagoryId)
                );

        savedCatagory.setCatagoryName(catagories.getCatagoryName());

        return catagoryRepository.save(savedCatagory);
    }
}
