package com.abhijit.ecomabhi.service;

import com.abhijit.ecomabhi.exceptions.APIException;
import com.abhijit.ecomabhi.exceptions.ResourceNotFoundException;
import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.payload.CatagoryDTO;
import com.abhijit.ecomabhi.payload.CatagoryResponse;
import com.abhijit.ecomabhi.repositories.CatagoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatagoryServiceImpl implements CatagoryService {

    @Autowired
    private CatagoryRepository catagoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CatagoryResponse getAllCatagories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                :  Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Catagories> catagoriesPage = catagoryRepository.findAll(pageDetails);
        List<Catagories> catagories = catagoriesPage.getContent();
        if (catagories.isEmpty()) {
            throw new APIException("No catagory created till now");
        }

        List<CatagoryDTO> catagoryDTOS = catagories.stream()
                .map(catagory -> modelMapper.map(catagory, CatagoryDTO.class))
                .toList();

        CatagoryResponse catagoryResponse = new CatagoryResponse();
        catagoryResponse.setContent(catagoryDTOS);
        catagoryResponse.setPageNumber(catagoriesPage.getNumber());
        catagoryResponse.setPageSize(catagoriesPage.getSize());
        catagoryResponse.setTotalElements(catagoriesPage.getTotalElements());
        catagoryResponse.setTotalPages(catagoriesPage.getTotalPages());
        catagoryResponse.setLastPage(catagoriesPage.isLast());
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
    public CatagoryDTO deleteCatagory(Long catagoryId) {

        Catagories catagory = catagoryRepository.findById(catagoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Catagory", "catagoryId", catagoryId)
                );

        catagoryRepository.delete(catagory);

        return modelMapper.map(catagory, CatagoryDTO.class);

    }

    @Override
    public CatagoryDTO updateCatagory(Long catagoryId, CatagoryDTO catagoryDTO) {
        Catagories savedCatagory = catagoryRepository.findById(catagoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Catagory", "catagoryId", catagoryId)
                );
        Catagories catagory = modelMapper.map(catagoryDTO, Catagories.class);
        savedCatagory.setCatagoryName(catagory.getCatagoryName());
        savedCatagory = catagoryRepository.save(savedCatagory);

        return modelMapper.map(savedCatagory, CatagoryDTO.class);
    }
}
