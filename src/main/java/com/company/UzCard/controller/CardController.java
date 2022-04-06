package com.company.UzCard.controller;

import com.company.UzCard.dto.CardDTO;
import com.company.UzCard.entity.CardEntity;
import com.company.UzCard.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/card")
@Api(tags = "Card Control")
public class CardController {
//    final Logger log = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private CardService cardService;

    @PostMapping
    @ApiOperation(value = "Creating new Card")
    public ResponseEntity<CardDTO> create(@RequestBody CardDTO dto) {
        log.info("New Card added {} ", dto);
        CardDTO response = cardService.create(dto);
        return ResponseEntity.ok(response);
    }


    @PutMapping
    @ApiOperation(value = "Increasing balance of Card")
    public ResponseEntity increaseBalance(@RequestParam("number") String number, @RequestParam("amount") long amount) {
        log.warn("Balance increased {} ", number);
        log.error("Balance increased {} ", number);
        cardService.increaseBalance(number, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getById(@PathVariable Integer id) {
        log.warn("Card got by id {} ", id);
        log.error("Card got by id {} ", id);
        return ResponseEntity.ok(cardService.getById(id));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<CardDTO> getByNumber(@PathVariable String number) {
        log.warn("Card got by id {} ", number);
        log.error("Card got by id {} ", number);
        return ResponseEntity.ok(cardService.getByNumber(number));
    }

    @GetMapping("/test/str")
    public ResponseEntity<String> test_str(){
        return ResponseEntity.ok("Test message");
    }

    @GetMapping("/test/param")
    public ResponseEntity<String> test_param(@RequestParam("size") int size,
                                             @RequestParam("page") int page){
        log.info("New Request Param: size:{}, page:{}", size, page);
        return ResponseEntity.ok("Test Message");
    }

    @GetMapping("/test/header")
    public ResponseEntity<String> test_header(){
        log.info("New Request Param: size:{}, page:{}");
        return ResponseEntity.ok("Test Message");
    }



}
