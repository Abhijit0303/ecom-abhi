package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.model.Catagories;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CatagoryServiceImpl implements CatagoryService {


    List<Catagories> catagoriesList = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Catagories> getAllCatagories() {
        return catagoriesList;
    }

    @Override
    public void createCatagory(Catagories catagories) {
        catagories.setCatagoryId(nextId++);
        catagoriesList.add(catagories);
    }

    @Override
    public String deleteCatagory(Long catagoryId) {
        Catagories catagories = catagoriesList.stream()
                .filter(c -> c.getCatagoryId().equals(catagoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no matching catagory with this id"));

            catagoriesList.remove(catagories);
            return "Catagory with ID " + catagoryId + " Deleted Successfully";

    }

    @Override
    public Catagories updateCatagory(Long catagoryId, Catagories catagories) {
        Optional<Catagories> optionalCatagories = catagoriesList.stream()
                .filter(c -> c.getCatagoryId().equals(catagoryId))
                .findFirst();

        if(optionalCatagories.isPresent()) {
            Catagories existingCatagories = optionalCatagories.get();
            existingCatagories.setCatagoryName(catagories.getCatagoryName());
            return existingCatagories;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no matching catagory with this id");
        }
    }
}
