package org.cook.lab7.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CelebrityDto {
    private Long id;
    private String name;
    private String surname;
    private List<String> pseudonyms;
    private LocalDate birthDay;
    private boolean alive;
}
