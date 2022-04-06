package com.company.UzCard.service;

import com.company.UzCard.dto.TransactionDTO;
import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.entity.TransactionEntity;
import com.company.UzCard.repository.CardRepository;
import com.company.UzCard.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    @Lazy
    private CardService cardService;
    @Autowired
    private CardRepository cardRepository;

    public TransactionDTO makeTransaction(String fromCardNumber, String toCardNUmber, Long amount) {
        CardEntity fromCard = cardService.get(fromCardNumber); // 1213
        CardEntity toCard = cardService.get(toCardNUmber);
        return doTransaction(fromCard, toCard, amount);
    }

    public TransactionDTO makeTransaction(TransactionDTO dto){

        CardEntity fromCard = cardService.get(dto.getFromCard());
        CardEntity toCard = cardService.get(dto.getToCard());

        return doTransaction(fromCard, toCard, dto.getAmount());

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TransactionDTO doTransaction(CardEntity fromCard, CardEntity toCard, Long amount) {
        Long balance = cardRepository.getCardBalance(fromCard.getNumber());
        if (balance == null || balance < amount) {
            throw new RuntimeException("Not enough balance.");
        }

        cardService.update_balance(fromCard.getNumber(), amount * -1);
        cardService.update_balance(toCard.getNumber(), amount);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setFromCardId(fromCard.getId());
        transactionEntity.setToCardId(toCard.getId());
        transactionEntity.setAmount(amount);
        transactionRepository.save(transactionEntity);
        return toDTO(transactionEntity);
    }


    public List<TransactionDTO> getTransactionByPid(Integer profileId){
        return transactionRepository.getTransactionByPid(profileId).stream().
                map(this::toDTO).collect(Collectors.toList());
    }

    public List<TransactionDTO> getDebitByPid(Integer profileId){
        return transactionRepository.getDebitByPid(profileId).stream().
                map(this::toDTO).collect(Collectors.toList());
    }

    public List<TransactionDTO> getCreditByPid(Integer profileId){
        return transactionRepository.getCreditByPid(profileId).stream().
                map(this::toDTO).collect(Collectors.toList());
    }

    public List<TransactionDTO> getTransactionByCid(Integer cardId){
        return transactionRepository.getTransactionByCid(cardId).stream().
                map(this::toDTO).collect(Collectors.toList());
    }

    public List<TransactionDTO> getCreditByCid(Integer cardId){
        return transactionRepository.getByFromCardId(cardId).stream().
                map(this::toDTO).collect(Collectors.toList());
    }

    public List<TransactionDTO> getDebitByCid(Integer cardId){
        return transactionRepository.getByToCardId(cardId).stream().
                map(this::toDTO).collect(Collectors.toList());
    }





    @Transactional(propagation = Propagation.NEVER)
    public boolean get() {
        transactionRepository.findAll();
        return true;
    }

    public TransactionDTO toDTO(TransactionEntity entity){
        TransactionDTO dto = new TransactionDTO();
        dto.setId(entity.getId());
        dto.setFromCard(entity.getFromCardId());
        dto.setToCard(entity.getToCardId());
        dto.setAmount(entity.getAmount());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;

    }

}
