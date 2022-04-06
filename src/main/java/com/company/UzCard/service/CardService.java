package com.company.UzCard.service;

import com.company.UzCard.dto.CardDTO;
import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.entity.ProfileEntity;
import com.company.UzCard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ProfileService profileService;

    public CardDTO create(CardDTO dto) {
        //ProfileEntity profile = profileService.get(userId);

        CardEntity entity = new CardEntity();
        entity.setNumber(dto.getNumber());
        entity.setBalance(0l);
        entity.setExcDate(dto.getExcDate());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setProfileId(dto.getProfileId());

        cardRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }



    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void increaseBalance(String number, Long balance) {
        Optional<CardEntity> optional = cardRepository.findByNumber(number);
        if (optional.isPresent()) {
            CardEntity entity = optional.get();
            entity.setBalance(entity.getBalance() + balance);
            cardRepository.save(entity);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update_balance(String number, Long balance) {
        cardRepository.updateBalance(number, balance);
    }

    public void get() {
        cardRepository.findByNumber("8600123412341234").orElseThrow(() -> new RuntimeException("Card nor found"));

    }

    public CardDTO getById(Integer id){
        return toDTO(get(id));
    }

    public CardDTO getByNumber(String number){
        return toDTO(get(number));
    }

    public CardEntity get(Integer id){
        if (id == null)
            return null;

        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card Not Found"));
    }


    public CardEntity get(String number) {
        return cardRepository.findByNumber(number).orElseThrow(() -> new RuntimeException("Card nor found"));
    }


    public CardDTO toDTO (CardEntity entity){
        CardDTO dto = new CardDTO();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setBalance(entity.getBalance());
        dto.setProfileId(entity.getProfile().getId());
        dto.setExcDate(entity.getExcDate());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
