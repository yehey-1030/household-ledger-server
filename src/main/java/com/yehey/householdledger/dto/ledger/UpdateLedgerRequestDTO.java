package com.yehey.householdledger.dto.ledger;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class UpdateLedgerRequestDTO extends PostLedgerRequestDTO {

  private Long ledgerID;
}
