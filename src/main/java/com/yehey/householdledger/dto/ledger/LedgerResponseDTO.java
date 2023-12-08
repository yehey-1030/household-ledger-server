package com.yehey.householdledger.dto.ledger;

import com.yehey.householdledger.dto.tag.TagResponseDTO;
import com.yehey.householdledger.entity.ArchiveType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class LedgerResponseDTO {
    private Long ledgerID;
    private String title;
    private LocalDate date;
    private Long amount;
    private String memo;
    private ArchiveType archiveType;
    private List<TagResponseDTO> tagList;
}
