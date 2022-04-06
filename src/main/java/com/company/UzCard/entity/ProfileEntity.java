package com.company.UzCard.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profilesss")
public class ProfileEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String surname;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "birth_date")
    private LocalDate birthDate;



}
