package com.yehey.householdledger.dto.statistics;

import com.yehey.householdledger.dto.ledger.LedgerResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class TagStatisticResponseDTO {
    private final String tagName;
    private final Long tagID;
    private final Long totalAmount;
    private final List<Long> ledgerList;
}
