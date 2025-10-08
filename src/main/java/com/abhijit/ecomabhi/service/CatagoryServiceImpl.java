package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.repositories.CatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CatagoryServiceImpl implements CatagoryService {

    @Autowired
    private CatagoryRepository catagoryRepository;

    @Override
    public List<Catagories> getAllCatagories() {
        return catagoryRepository.findAll();
    }

    @Override
    public void createCatagory(Catagories catagories) {
//        catagories.setCatagoryId(nextId++);
        catagoryRepository.save(catagories);
    }

    @Override
    public String deleteCatagory(Long catagoryId) {

        Catagories catagory = catagoryRepository.findById(catagoryId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, " There is no matchin catagory with this id")
                );

        catagoryRepository.delete(catagory);

        return "Catagory with ID " + catagoryId + " Deleted Successfully";

    }

    @Override
    public Catagories updateCatagory(Long catagoryId, Catagories catagories) {
        Catagories savedCatagory = catagoryRepository.findById(catagoryId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no matching catagory with this id")
                );

        savedCatagory.setCatagoryName(catagories.getCatagoryName());

        return catagoryRepository.save(savedCatagory);
    }
}
