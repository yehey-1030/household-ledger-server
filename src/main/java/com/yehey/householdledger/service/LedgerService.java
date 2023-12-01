package com.yehey.householdledger.service;

import com.yehey.householdledger.dto.ledger.PostLedgerRequestDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.Ledger;
import com.yehey.householdledger.entity.Tag;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.LedgerRepository;
import com.yehey.householdledger.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LedgerService {
    private final TagRepository tagRepository;
    private final ArchiveTypeRepository archiveTypeRepository;
    private final LedgerRepository ledgerRepository;

    public void saveLedger(PostLedgerRequestDTO dto){
        ArchiveType archiveType = archiveTypeRepository.findByArchiveTypeID(dto.getTypeID());
        List<Tag> tagList = new ArrayList<>();

        for (Long tagInput: dto.getTagList()){
            Tag tag = tagRepository.findByTagID(tagInput);
            tagList.add(tag);
        }

        ledgerRepository.save(Ledger.builder()
                        .archiveTypeID(archiveType)
                        .amount(dto.getAmount())
                        .date(dto.getDate())
                        .title(dto.getTitle())
                        .linkedTags(tagList)
                        .memo(dto.getMemo())
                        .build());

    }

}
