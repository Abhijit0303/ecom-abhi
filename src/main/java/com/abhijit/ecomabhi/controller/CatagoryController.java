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
    public ResponseEntity<CatagoryDTO> deleteCatagory(@PathVariable Long catagoryId) {
        com.abhijit.ecomabhi.payload.CatagoryDTO deletedCatagoryDTO = catagoryService.deleteCatagory(catagoryId);
        return new ResponseEntity<>(deletedCatagoryDTO, HttpStatus.OK);
    }

//    @PutMapping("/public/categories/{catagoryId}")
    @RequestMapping(value = "/public/categories/{catagoryId}", method = RequestMethod.PUT)
    public ResponseEntity<CatagoryDTO>  updateCatagory(@PathVariable Long catagoryId, @RequestBody CatagoryDTO catagoryDTO) {
        CatagoryDTO savedCatagoryDTO = catagoryService.updateCatagory(catagoryId, catagoryDTO);
        return new ResponseEntity<>(savedCatagoryDTO, HttpStatus.OK);
    }
}
