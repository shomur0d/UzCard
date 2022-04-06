package com.company.UzCard.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String middleName;
    private LocalDate birthDate;
    private LocalDateTime createdDate;
}
