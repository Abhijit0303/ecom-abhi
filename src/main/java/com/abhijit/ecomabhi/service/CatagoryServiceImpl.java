package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.repositories.CatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Catagories> catagoriesList = catagoryRepository.findAll();
        Catagories catagories = catagoriesList.stream()
                .filter(c -> c.getCatagoryId().equals(catagoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no matching catagory with this id"));

            catagoryRepository.delete(catagories);
            return "Catagory with ID " + catagoryId + " Deleted Successfully";

    }

    @Override
    public Catagories updateCatagory(Long catagoryId, Catagories catagories) {
        List<Catagories> catagoriesList = catagoryRepository.findAll();

        Optional<Catagories> optionalCatagories = catagoriesList.stream()
                .filter(c -> c.getCatagoryId().equals(catagoryId))
                .findFirst();

        if(optionalCatagories.isPresent()) {
            Catagories existingCatagories = optionalCatagories.get();
            existingCatagories.setCatagoryName(catagories.getCatagoryName());
            Catagories savedCatagory = catagoryRepository.save(existingCatagories);
            return savedCatagory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no matching catagory with this id");
        }
    }
}
