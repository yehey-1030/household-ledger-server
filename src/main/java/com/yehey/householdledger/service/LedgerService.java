package com.yehey.householdledger.service;

import com.yehey.householdledger.dto.ArchiveTypeDTO;
import com.yehey.householdledger.dto.ledger.LedgerResponseDTO;
import com.yehey.householdledger.dto.ledger.PostLedgerRequestDTO;
import com.yehey.householdledger.dto.tag.TagResponseDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.Ledger;
import com.yehey.householdledger.entity.Tag;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.LedgerRepository;
import com.yehey.householdledger.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<LedgerResponseDTO> getLedgerByMonth(String target){

        LocalDate start = LocalDate.parse(target+"-01");
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Ledger> ledgers= ledgerRepository.getAllByDateBetweenOrderByDate(start, end);

        List<LedgerResponseDTO> result = new ArrayList<>();

        for (Ledger ledger:ledgers){
            List<TagResponseDTO> tagList = new ArrayList<>();
            for (Tag tag:ledger.getLinkedTags()){
                TagResponseDTO tagged = TagResponseDTO.builder()
                        .name(tag.getName())
                        .tagID(tag.getTagID())
                        .build();
                tagList.add(tagged);
            }

            LedgerResponseDTO dto = LedgerResponseDTO.builder()
                    .ledgerID(ledger.getLedgerID())
                    .title(ledger.getTitle())
                    .date(ledger.getDate())
                    .amount(ledger.getAmount())
                    .memo(ledger.getMemo())
                    .archiveType(ledger.getArchiveTypeID())
                    .tagList(tagList)
                    .build();

            result.add(dto);
        }

        return result;
    }

    public void deleteLedgerByID(Long ledgerID){
        Ledger ledger = ledgerRepository.getReferenceById(ledgerID);
        ledger.setArchiveTypeID(null);
        ledger.setLinkedTags(null);

        ledgerRepository.delete(ledger);
    }
}
