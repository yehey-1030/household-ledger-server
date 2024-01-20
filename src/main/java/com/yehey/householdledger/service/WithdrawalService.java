package com.yehey.householdledger.service;

import com.yehey.householdledger.dto.tag.TagResponseDTO;
import com.yehey.householdledger.dto.withdrawal.PostWithdrawalRequestDTO;
import com.yehey.householdledger.dto.withdrawal.WithdrawalResponseDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.AutomaticWithdrawal;
import com.yehey.householdledger.entity.Tag;
import com.yehey.householdledger.entity.WithdrawalTagRelation;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.TagRepository;
import com.yehey.householdledger.repository.WithdrawalRepostiory;
import com.yehey.householdledger.repository.WithdrawalTagRelataionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawalService {

  private final ArchiveTypeRepository archiveTypeRepository;
  private final TagRepository tagRepository;
  private final WithdrawalRepostiory withdrawalRepostiory;
  private final WithdrawalTagRelataionRepository withdrawalTagRelataionRepository;

  public void saveWithdrawal(PostWithdrawalRequestDTO dto) {
    ArchiveType archiveType = archiveTypeRepository.findByArchiveTypeID(dto.getArchiveTypeID());

    AutomaticWithdrawal newWithdrawal = AutomaticWithdrawal.builder()
        .amount(dto.getAmount())
        .archiveTypeID(archiveType)
        .cycle(dto.getCycle())
        .isExcluded(dto.getIsExcluded())
        .memo(dto.getMemo())
        .title(dto.getTitle())
        .build();

    withdrawalRepostiory.save(newWithdrawal);

    for (Long tagID : dto.getTagList()) {
      Tag tag = tagRepository.findByTagID(tagID);
      withdrawalTagRelataionRepository.save(
          WithdrawalTagRelation.builder().tag(tag).withdrawal(newWithdrawal).build());
    }

  }

  public List<TagResponseDTO> getRelatedTagList(AutomaticWithdrawal withdrawal) {
    List<TagResponseDTO> tagResponseDTOList = new ArrayList<>();

    for (WithdrawalTagRelation relation : withdrawal.getRelation()) {
      Tag tag = relation.getTag();
      TagResponseDTO tagged = TagResponseDTO.builder().name(tag.getName()).tagID(tag.getTagID())
          .build();
      tagResponseDTOList.add(tagged);
    }

    return tagResponseDTOList;
  }

  public List<WithdrawalResponseDTO> getAllWithdrawalList() {
    List<AutomaticWithdrawal> withdrawalList = withdrawalRepostiory.findAll(
        Sort.by(Sort.Direction.ASC, "cycle"));
    List<WithdrawalResponseDTO> withdrawalResponseDTOList = new ArrayList<>();

    for (AutomaticWithdrawal withdrawal : withdrawalList) {
      List<TagResponseDTO> tagResponseDTOList = getRelatedTagList(withdrawal);

      withdrawalResponseDTOList.add(WithdrawalResponseDTO.builder()
          .withdrawalID(withdrawal.getWithdrawalID())
          .cycle(withdrawal.getCycle())
          .amount(withdrawal.getAmount())
          .memo(withdrawal.getMemo())
          .isExcluded(withdrawal.getIsExcluded())
          .title(withdrawal.getTitle())
          .archiveType(withdrawal.getArchiveTypeID())
          .tagList(tagResponseDTOList)
          .build());
    }

    return withdrawalResponseDTOList;
  }
}
