package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.exceptions.APIException;
import com.abhijit.ecomabhi.exceptions.ResourceNotFoundException;
import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.payload.CatagoryDTO;
import com.abhijit.ecomabhi.payload.CatagoryResponse;
import com.abhijit.ecomabhi.repositories.CatagoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatagoryServiceImpl implements CatagoryService {

    @Autowired
    private CatagoryRepository catagoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CatagoryResponse getAllCatagories() {
        List<Catagories> catagories = catagoryRepository.findAll();
        if (catagories.isEmpty()) {
            throw new APIException("No catagory created till now");
        }

        List<CatagoryDTO> catagoryDTOS = catagories.stream()
                .map(catagory -> modelMapper.map(catagory, CatagoryDTO.class))
                .toList();

        CatagoryResponse catagoryResponse = new CatagoryResponse();
        catagoryResponse.setContent(catagoryDTOS);
        return catagoryResponse;
    }

    @Override
    public CatagoryDTO createCatagory(CatagoryDTO catagoryDTO) {
        Catagories catagories = modelMapper.map(catagoryDTO, Catagories.class);
        Catagories catagoryFromDB = catagoryRepository.findByCatagoryName(catagories.getCatagoryName());
        if  (catagoryFromDB != null) {
            throw new APIException("Catagory with the name " + catagoryDTO.getCatagoryName() + " already exists !!!");
        }
        Catagories savedCatagories = catagoryRepository.save(catagories);
        return modelMapper.map(savedCatagories, CatagoryDTO.class);

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
