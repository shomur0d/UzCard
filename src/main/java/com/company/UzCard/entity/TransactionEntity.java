package com.company.UzCard.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transaction")
public class TransactionEntity extends UUIDBaseEntity{
    @ManyToOne
    @JoinColumn(name = "from_card_id", insertable = false, updatable = false)
    private CardEntity fromCard;
    @ManyToOne
    @JoinColumn(name = "to_card_id", insertable = false, updatable = false)
    private CardEntity toCard;

    @Column(name = "from_card_id")
    private Integer fromCardId;

    @Column(name = "to_card_id")
    private Integer toCardId;

    @Column(name = "amount")
    private Long amount;

}
