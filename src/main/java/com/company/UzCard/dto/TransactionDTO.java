package com.company.UzCard.dto;

import com.company.UzCard.entity.CardEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private String id;
    private LocalDateTime createdDate;
    private Integer fromCard;
    private Integer toCard;
    private Long amount;

}
