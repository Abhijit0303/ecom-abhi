package com.abhijit.ecomabhi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatagoryDTO {
    private Long catagoryId;
    private String catagoryName;
}
