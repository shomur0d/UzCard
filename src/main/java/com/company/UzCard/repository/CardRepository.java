package com.company.UzCard.repository;

import com.company.UzCard.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {

    Optional<CardEntity> findByNumber(String number);

    @Transactional
    @Modifying
    @Query("update CardEntity as s set s.balance = s.balance + :amount where s.number =:number")
    void updateBalance(@Param("number") String number, @Param("amount") Long amount);

    @Transactional
    @Query("Select s.balance from CardEntity s where s.number =:number ")
    public Long getCardBalance(@Param("number") String number);

}
