package com.yehey.householdledger.service;

import com.yehey.householdledger.dto.withdrawal.PostWithdrawalRequestDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.AutomaticWithdrawal;
import com.yehey.householdledger.entity.Tag;
import com.yehey.householdledger.entity.WithdrawalTagRelation;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.TagRepository;
import com.yehey.householdledger.repository.WithdrawalRepostiory;
import com.yehey.householdledger.repository.WithdrawalTagRelataionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawalService {
    private final ArchiveTypeRepository archiveTypeRepository;
    private final TagRepository tagRepository;
    private final WithdrawalRepostiory withdrawalRepostiory;
    private final WithdrawalTagRelataionRepository withdrawalTagRelataionRepository;

    public void saveWithdrawal(PostWithdrawalRequestDTO dto){
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

        for (Long tagID:dto.getTagList()){
            Tag tag = tagRepository.findByTagID(tagID);
            withdrawalTagRelataionRepository.save(WithdrawalTagRelation.builder().tag(tag).withdrawal(newWithdrawal).build());
        }

    }
}
