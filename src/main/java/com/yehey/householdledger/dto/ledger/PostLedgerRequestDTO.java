package com.yehey.householdledger.dto.ledger;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostLedgerRequestDTO {
    private String title;
    private Long typeID;
    private Long amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDate date;
    private List<Long> tagList;
    private String memo;
    private Boolean isExcluded;
}
