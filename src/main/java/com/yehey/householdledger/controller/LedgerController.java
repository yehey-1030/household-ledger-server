package com.yehey.householdledger.controller;

import com.yehey.householdledger.dto.ledger.LedgerRequestDTO;
import com.yehey.householdledger.dto.ledger.LedgerResponseDTO;
import com.yehey.householdledger.dto.ledger.PostLedgerRequestDTO;
import com.yehey.householdledger.dto.response.DataResponseDTO;
import com.yehey.householdledger.dto.response.ErrorResponseDTO;
import com.yehey.householdledger.dto.response.ResponseDTO;
import com.yehey.householdledger.entity.Ledger;
import com.yehey.householdledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @GetMapping("/ledgers/current")
    public ResponseDTO getCurrentMonthLedgers(){
        LocalDate localDateNow = LocalDate.now();
        String parsedLocalDateTimeNow = localDateNow.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        List<LedgerResponseDTO> ledgerDTO = ledgerService.getLedgerByMonth(parsedLocalDateTimeNow);
        return DataResponseDTO.of(ledgerDTO,"get current month data success");
    }

    @GetMapping("/ledgers")
    public ResponseDTO getLedgerListByDate(
        LedgerRequestDTO dto
    ){
        List<LedgerResponseDTO> ledgerDTO  = ledgerService.getLedgerListByDate(dto);
        return DataResponseDTO.of(ledgerDTO,"get data success");
    }

    @GetMapping("/ledgers/{ledgerID}")
    public ResponseDTO getLedgerByID(
            @PathVariable(name = "ledgerID") Long ledgerID
    ){
        LedgerResponseDTO responseDTO = ledgerService.getLedgerByID(ledgerID);
        return DataResponseDTO.of(responseDTO);
    }

    @DeleteMapping("/ledgers/{ledgerID}")
    public ResponseDTO deleteLedgerByID(
            @PathVariable(name = "ledgerID") Long ledgerID) {
        ledgerService.deleteLedgerByID(ledgerID);
        return ResponseDTO.of("success",0,"delete "+ledgerID+"successed");
    }

}

