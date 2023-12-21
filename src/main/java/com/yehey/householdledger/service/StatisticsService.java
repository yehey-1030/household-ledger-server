package com.yehey.householdledger.service;

import com.yehey.householdledger.dto.statistics.StatisticRequestDTO;
import com.yehey.householdledger.dto.statistics.TotalResponseDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.LedgerRepository;
import com.yehey.householdledger.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final LedgerRepository ledgerRepository;
    private final ArchiveTypeRepository archiveTypeRepository;
    private final TagRepository tagRepository;

    public TotalResponseDTO getTotalAmountByDate(StatisticRequestDTO dto){
        Long totalSum = ledgerRepository.getTotalSumByDateAndArchiveTypeID(dto.getArchiveTypeID(),dto.getStart(),dto.getEnd());
        if (totalSum==null){
            totalSum = 0L;
        }
        return TotalResponseDTO.builder().totalResult(totalSum).build();
    }
}
