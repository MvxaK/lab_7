package org.cook.lab7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto {

    private Long id;
    private String name;
    private BigDecimal budget;
    private List<Long> celebritiesId;
    
}
