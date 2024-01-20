package com.yehey.householdledger.dto.withdrawal;

import com.yehey.householdledger.dto.tag.TagResponseDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.AutomaticWithdrawal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
public class WithdrawalResponseDTO {

  private final Long withdrawalID;
  private final String title;
  private final String cycle;
  private final Long amount;
  private final String memo;
  private final ArchiveType archiveType;
  private final List<TagResponseDTO> tagList;
  private final Boolean isExcluded;
}
