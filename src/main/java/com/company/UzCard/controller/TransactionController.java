package com.company.UzCard.controller;

import com.company.UzCard.dto.TransactionDTO;
import com.company.UzCard.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transaction")
@Api(tags = "Transaction Control")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @ApiOperation(value = "Make Transaction")
    public ResponseEntity<TransactionDTO> create(@RequestParam("from") String from,
                                                 @RequestParam("to") String to, @RequestParam("amount") long amount) {
        log.warn("Transaction made {} ", from, to, amount);
        log.error("Transaction made {} ", from, to, amount);
        TransactionDTO response = transactionService.makeTransaction(from, to, amount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pid/{pid}")
    @ApiOperation(value = "Get All Transactions by Profile id")
    public ResponseEntity getAllByPid(@PathVariable Integer pid) {
        log.warn("Transactions by profile id {} ", pid);
        log.error("Transactions by profile id {} ", pid);
        return ResponseEntity.ok(transactionService.getTransactionByPid(pid));
    }

    @GetMapping("/debit/pid/{pid}")
    @ApiOperation(value = "Get All Debit Transactions by Profile id")
    public ResponseEntity getDebitByPid(@PathVariable Integer pid) {
        log.warn("Debit Transactions by profile id {} ", pid);
        log.error("Debit Transactions by profile id {} ", pid);
        return ResponseEntity.ok(transactionService.getDebitByPid(pid));
    }

    @GetMapping("/credit/pid/{pid}")
    @ApiOperation(value = "Get All Credit Transactions by Profile id")
    public ResponseEntity getCreditPid(@PathVariable Integer pid) {
        log.warn("Credit Transactions by profile id {} ", pid);
        log.error("Credit Transactions by profile id {} ", pid);
        return ResponseEntity.ok(transactionService.getCreditByPid(pid));
    }

    @GetMapping("/cid/{cid}")
    @ApiOperation(value = "Get All Transactions by Card id")
    public ResponseEntity getAllByCid(@PathVariable Integer cid) {
        log.warn("Transactions by card id {} ", cid);
        log.error("Transactions by card id {} ", cid);
        return ResponseEntity.ok(transactionService.getTransactionByCid(cid));
    }

    @GetMapping("/debit/cid/{cid}")
    @ApiOperation(value = "Get All Debit Transactions by Card id")
    public ResponseEntity getDebitByCid(@PathVariable Integer cid) {
        log.warn("Debit Transactions by card id {} ", cid);
        log.error("Debit Transactions by card id {} ", cid);
        return ResponseEntity.ok(transactionService.getDebitByCid(cid));
    }

    @GetMapping("/credit/cid/{cid}")
    @ApiOperation(value = "Get All Debit Transactions by Profile id")
    public ResponseEntity getCreditByCid(@PathVariable Integer cid) {
        log.warn("Credit Transactions by card id {} ", cid);
        log.error("Credit Transactions by card id {} ", cid);
        return ResponseEntity.ok(transactionService.getCreditByCid(cid));
    }


}
