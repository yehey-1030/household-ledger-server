package com.yehey.householdledger.controller;

import com.yehey.householdledger.dto.ledger.PostLedgerRequestDTO;
import com.yehey.householdledger.dto.response.ErrorResponseDTO;
import com.yehey.householdledger.dto.response.ResponseDTO;
import com.yehey.householdledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
@Slf4j
@RequiredArgsConstructor
public class LedgerController {

    private final LedgerService ledgerService;

    @PostMapping("/ledgers")
    public ResponseDTO postLedger(
            @RequestBody PostLedgerRequestDTO dto
            ){
            if (dto.getTitle()==null){
                return ErrorResponseDTO.of(400,"bad request");
            }

            ledgerService.saveLedger(dto);
            return ResponseDTO.of("success",201,"create success");
    }

}

