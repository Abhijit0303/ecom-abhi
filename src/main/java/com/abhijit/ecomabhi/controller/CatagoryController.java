package com.abhijit.ecomabhi.controller;

import com.abhijit.ecomabhi.model.Catagories;
import com.abhijit.ecomabhi.service.CatagoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CatagoryController {
    @Autowired
    private CatagoryService catagoryService;

//    @GetMapping("/public/categories")
    @RequestMapping(value = "/public/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Catagories>> getCatagoriesList() {

        List<Catagories> allCatagories = catagoryService.getAllCatagories();
        return ResponseEntity.ok(allCatagories);
    }

//    @PostMapping("/public/categories")
    @RequestMapping(value = "/public/categories", method = RequestMethod.POST)
    public ResponseEntity<String> addCatagory(@Valid @RequestBody Catagories catagories) {
        catagoryService.createCatagory(catagories);
        return new ResponseEntity<>("Catagory created successfully", HttpStatus.CREATED);
    }

//    @DeleteMapping("/admin/categories/{catagoryId}")
    @RequestMapping(value = "/admin/categories/{catagoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCatagory(@PathVariable Long catagoryId) {
        try{
            String Status = catagoryService.deleteCatagory(catagoryId);
            return new ResponseEntity<>(Status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }

    }

//    @PutMapping("/public/categories/{catagoryId}")
    @RequestMapping(value = "/public/categories/{catagoryId}", method = RequestMethod.PUT)
    public ResponseEntity<String>  updateCatagory(@PathVariable Long catagoryId, @RequestBody Catagories catagories) {
        try {
            Catagories Status = catagoryService.updateCatagory(catagoryId, catagories);
            return new ResponseEntity<>("Catagory with ID " + catagoryId + " updated sucessfully", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
