package com.abhijit.ecomabhi.controller;

import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.payload.CatagoryDTO;
import com.abhijit.ecomabhi.payload.CatagoryResponse;
import com.abhijit.ecomabhi.service.CatagoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CatagoryController {
    @Autowired
    private CatagoryService catagoryService;

//    @GetMapping("/public/categories")
    @RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    public ResponseEntity<CatagoryResponse> getCatagoriesList() {

        CatagoryResponse catagoryResponse= catagoryService.getAllCatagories();
        return ResponseEntity.ok(catagoryResponse);
    }

//    @PostMapping("/public/categories")
    @RequestMapping(value = "/public/categories", method = RequestMethod.POST)
    public ResponseEntity<CatagoryDTO> addCatagory(@Valid @RequestBody CatagoryDTO catagoryDTO) {
        CatagoryDTO savedCatagoryDTO = catagoryService.createCatagory(catagoryDTO);
        return new ResponseEntity<>(savedCatagoryDTO, HttpStatus.CREATED);
    }

//    @DeleteMapping("/admin/categories/{catagoryId}")
    @RequestMapping(value = "/admin/categories/{catagoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCatagory(@PathVariable Long catagoryId) {
        String Status = catagoryService.deleteCatagory(catagoryId);
        return new ResponseEntity<>(Status, HttpStatus.OK);
    }

//    @PutMapping("/public/categories/{catagoryId}")
    @RequestMapping(value = "/public/categories/{catagoryId}", method = RequestMethod.PUT)
    public ResponseEntity<String>  updateCatagory(@PathVariable Long catagoryId, @RequestBody Catagories catagories) {
        Catagories savedCatagory = catagoryService.updateCatagory(catagoryId, catagories);
        return new ResponseEntity<>("Catagory with ID " + catagoryId + " updated sucessfully", HttpStatus.OK);
    }
}
