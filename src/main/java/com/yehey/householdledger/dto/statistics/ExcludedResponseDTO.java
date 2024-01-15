package com.yehey.householdledger.dto.statistics;

import com.yehey.householdledger.entity.Ledger;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class ExcludedResponseDTO {
    private final List<Long> ledgerList;
    private final Long total;
}
