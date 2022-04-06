package com.company.UzCard.dto;

import com.company.UzCard.entity.ProfileEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class CardDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private String number;
    private String excDate;
    private Long balance;
    private Integer profileId;
}
