package com.company.UzCard.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "card")
public class CardEntity extends BaseEntity {
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @Column(name = "exc_Date")
    private String excDate;
    @Column(name = "balance")
    private Long balance;

    @Column(name = "profile_id", nullable = false)
    private Integer profileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;


}
