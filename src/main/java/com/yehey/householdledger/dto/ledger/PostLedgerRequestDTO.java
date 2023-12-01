package com.yehey.householdledger.dto.ledger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostLedgrRequestDTO {
    private String title;
    private Long typeID;
    private Long amount;
}
