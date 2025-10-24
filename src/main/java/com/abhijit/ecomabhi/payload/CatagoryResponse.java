package com.abhijit.ecomabhi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatagoryResponse {
    private List<CatagoryDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
