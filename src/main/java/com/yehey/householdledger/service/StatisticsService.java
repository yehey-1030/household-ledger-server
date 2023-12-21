package com.yehey.householdledger.service;

import com.yehey.householdledger.dto.statistics.StatisticRequestDTO;
import com.yehey.householdledger.dto.statistics.TagStatisticResponseDTO;
import com.yehey.householdledger.dto.statistics.TotalResponseDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.Ledger;
import com.yehey.householdledger.entity.Tag;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.LedgerRepository;
import com.yehey.householdledger.repository.TagLedgerRelationRepository;
import com.yehey.householdledger.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final LedgerRepository ledgerRepository;
    private final ArchiveTypeRepository archiveTypeRepository;
    private final TagRepository tagRepository;
    private final TagLedgerRelationRepository tagLedgerRelationRepository;

    public TotalResponseDTO getTotalAmountByDate(StatisticRequestDTO dto){
        Long totalSum = ledgerRepository.getTotalSumByDateAndArchiveTypeID(dto.getArchiveTypeID(),dto.getStart(),dto.getEnd());
        return TotalResponseDTO.builder().totalResult(totalSum).build();
    }

    public TagStatisticResponseDTO getTagStatistic(StatisticRequestDTO dto, Long tagID){
        Tag tag = tagRepository.findByTagID(tagID);
        Long totalSum = tagLedgerRelationRepository.getTotalSumByTagAndDate(tagID,dto.getStart(),dto.getEnd(),dto.getArchiveTypeID());

        List<Long> ledgerList = tagLedgerRelationRepository.getLedgerListByTagAndDate(tagID,dto.getStart(),dto.getEnd(),dto.getArchiveTypeID());

        return TagStatisticResponseDTO.builder().totalAmount(totalSum).tagName(tag.getName()).ledgerList(ledgerList).tagID(tag.getTagID()).build();
    }

    public List<TagStatisticResponseDTO> getRootTagStatistics(StatisticRequestDTO dto){
        ArchiveType archiveType = archiveTypeRepository.findByArchiveTypeID(dto.getArchiveTypeID());

        List<Tag> rootTagList = tagRepository.findAllByArchiveTypeIDAndParentID(archiveType,null);

        List<TagStatisticResponseDTO> tagStatisticResponseDTOList = new ArrayList<>();

        for (Tag tag:rootTagList){
            tagStatisticResponseDTOList.add(getTagStatistic(dto,tag.getTagID()));
        }

        return tagStatisticResponseDTOList;
    }
}
