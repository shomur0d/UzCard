package com.company.UzCard.repository;

import com.company.UzCard.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {


    @Query(value = "select * from transaction \n" +
            "t where t.from_card_id in(select id from card c where c.profile_id in(select id from profilesss p where p.id = :id))\n" +
            "or to_card_id in (select id from card c where c.profile_id in(select id from profilesss p where p.id = :id))", nativeQuery = true)
    List<TransactionEntity> getTransactionByPid(@Param("id") Integer id);


    @Query(value = "select * from transaction \n" +
            "t where t.to_card_id in(select id from card c where c.profile_id in(select id from profilesss p where p.id = :id))", nativeQuery = true)
    List<TransactionEntity> getDebitByPid(@Param("id") Integer id);


    @Query(value = "select * from transaction \n" +
            "t where t.from_card_id in(select id from card c where c.profile_id in(select id from profilesss p where p.id = :id))", nativeQuery = true)
    List<TransactionEntity> getCreditByPid(@Param("id") Integer id);

    @Query(value = "select * from transaction \n" +
            "t where t.to_card_id = :id or t.from_card_id = :id", nativeQuery = true)
    List<TransactionEntity> getTransactionByCid(@Param("id") Integer id);

    List<TransactionEntity> getByFromCardId(Integer id);

    List<TransactionEntity> getByToCardId(Integer id);

}
