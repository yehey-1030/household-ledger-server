package com.yehey.householdledger.dto.statistics;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TotalResponseDTO {

  private final Long totalAmount;
}

