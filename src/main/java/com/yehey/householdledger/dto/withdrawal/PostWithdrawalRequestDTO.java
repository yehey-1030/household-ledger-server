package com.yehey.householdledger.dto.withdrawal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostWithdrawalRequestDTO {

  private final String cycle;
  private final String title;
  private final Long amount;
  private final String memo;
  private final Boolean isExcluded;
  private final List<Long> tagList;
  private final Long archiveTypeID;
}
