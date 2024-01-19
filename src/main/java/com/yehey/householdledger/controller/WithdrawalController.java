package com.yehey.householdledger.controller;


import com.yehey.householdledger.dto.response.ResponseDTO;
import com.yehey.householdledger.dto.withdrawal.PostWithdrawalRequestDTO;
import com.yehey.householdledger.service.WithdrawalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/withdrawals")
@Slf4j
@RequiredArgsConstructor
public class WithdrawalController {
    private final WithdrawalService withdrawalService;

    @PostMapping("")
    public ResponseDTO postWithdrawal(
            @RequestBody PostWithdrawalRequestDTO dto
    ){
        withdrawalService.saveWithdrawal(dto);
        return ResponseDTO.of("success",0,"create withdrawal success");
    }

}
