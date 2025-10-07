package com.abhijit.ecomabhi.model;



public class Catagories {

    private Long CatagoryId;
    private String CatagoryName;

    public Catagories(Long catagoryId, String catagoryName) {
        CatagoryId = catagoryId;
        CatagoryName = catagoryName;
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
