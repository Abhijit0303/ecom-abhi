package com.abhijit.ecomabhi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "catagories")
public class Catagories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CatagoryId;
    private String CatagoryName;

    public Catagories(Long catagoryId, String catagoryName) {
        CatagoryId = catagoryId;
        CatagoryName = catagoryName;
    }

    public Catagories() {

    }

    public Long getCatagoryId() {
        return CatagoryId;
    }

    public void setCatagoryId(Long catagoryId) {
        CatagoryId = catagoryId;
    }

    public String getCatagoryName() {
        return CatagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        CatagoryName = catagoryName;
    }
}
