package com.yehey.householdledger.service;

import com.yehey.householdledger.dto.ArchiveTypeDTO;
import com.yehey.householdledger.dto.ledger.LedgerRequestDTO;
import com.yehey.householdledger.dto.ledger.LedgerResponseDTO;
import com.yehey.householdledger.dto.ledger.PostLedgerRequestDTO;
import com.yehey.householdledger.dto.tag.TagResponseDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.Ledger;
import com.yehey.householdledger.entity.Tag;
import com.yehey.householdledger.entity.TagLedgerRelation;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.LedgerRepository;
import com.yehey.householdledger.repository.TagLedgerRelationRepository;
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
    private final TagLedgerRelationRepository tagLedgerRelationRepository;

    public void saveLedger(PostLedgerRequestDTO dto){
        ArchiveType archiveType = archiveTypeRepository.findByArchiveTypeID(dto.getTypeID());

        Ledger newLedger = Ledger.builder()
                .archiveTypeID(archiveType)
                .amount(dto.getAmount())
                .date(dto.getDate())
                .title(dto.getTitle())
                .memo(dto.getMemo())
                .build();
        ledgerRepository.save(newLedger);

        for (Long tagInput: dto.getTagList()){
            Tag tag = tagRepository.findByTagID(tagInput);
            tagLedgerRelationRepository.save(TagLedgerRelation.builder().tag(tag).ledger(newLedger).build());

        }

    }

    public List<LedgerResponseDTO> getLedgerByMonth(String target){

        LocalDate start = LocalDate.parse(target+"-01");
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        LedgerRequestDTO dto = new LedgerRequestDTO(start,end);

        return getLedgerListByDate(dto);

    }

    public void deleteLedgerByID(Long ledgerID){
        Ledger ledger = ledgerRepository.getReferenceById(ledgerID);
        ledger.setArchiveTypeID(null);
        ledger.setRelation(null);

        ledgerRepository.delete(ledger);
    }

    public List<TagResponseDTO> getRelatedTagList(Ledger ledger){
        List<TagResponseDTO> tagList = new ArrayList<>();
        for (TagLedgerRelation relation:ledger.getRelation()){
            Tag tag= relation.getTag();
            TagResponseDTO tagged = TagResponseDTO.builder()
                    .name(tag.getName())
                    .tagID(tag.getTagID())
                    .build();
            tagList.add(tagged);
        }
        return tagList;
    }

    public LedgerResponseDTO getLedgerByID(Long ledgerID){
        Ledger ledger = ledgerRepository.getReferenceById(ledgerID);

        List<TagResponseDTO> tagResponseDTOList = getRelatedTagList(ledger);

        return LedgerResponseDTO.builder()
                .ledgerID(ledger.getLedgerID())
                .title(ledger.getTitle())
                .date(ledger.getDate())
                .amount(ledger.getAmount())
                .memo(ledger.getMemo())
                .tagList(tagResponseDTOList)
        .build();

    }

    public List<LedgerResponseDTO> getLedgerListByDate(LedgerRequestDTO dto){
        List<Ledger> ledgers = ledgerRepository.getAllByDateBetweenOrderByDate(dto.getStart(),dto.getEnd());

        List<LedgerResponseDTO> result = new ArrayList<>();

        for (Ledger ledger:ledgers){
            List<TagResponseDTO> tagList = getRelatedTagList(ledger);

            LedgerResponseDTO responseDTO = LedgerResponseDTO.builder()
                    .ledgerID(ledger.getLedgerID())
                    .title(ledger.getTitle())
                    .date(ledger.getDate())
                    .amount(ledger.getAmount())
                    .memo(ledger.getMemo())
                    .archiveType(ledger.getArchiveTypeID())
                    .tagList(tagList)
                    .build();

            result.add(responseDTO);
        }

        return result;
    }
}
