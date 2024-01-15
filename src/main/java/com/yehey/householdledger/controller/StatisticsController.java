package com.yehey.householdledger.controller;

import com.yehey.householdledger.dto.response.DataResponseDTO;
import com.yehey.householdledger.dto.response.ResponseDTO;
import com.yehey.householdledger.dto.statistics.ExcludedResponseDTO;
import com.yehey.householdledger.dto.statistics.StatisticRequestDTO;
import com.yehey.householdledger.dto.statistics.TagStatisticResponseDTO;
import com.yehey.householdledger.dto.statistics.TotalResponseDTO;
import com.yehey.householdledger.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/statistic")
@Slf4j
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/total")
    public ResponseDTO getTotalReport(
            StatisticRequestDTO dto
    ){
        TotalResponseDTO totalResponseDTO = statisticsService.getTotalAmountByDate(dto);
        return DataResponseDTO.of(totalResponseDTO,dto.getArchiveTypeID()+"total amount get successed from "+dto.getStart()+" to "+dto.getEnd());
    }

    @GetMapping("/tags")
    public ResponseDTO getTotalRootTagReport(
            StatisticRequestDTO dto
    ){
        List<TagStatisticResponseDTO> tagStatisticResponseDTOList = statisticsService.getRootTagStatistics(dto);
        return DataResponseDTO.of(tagStatisticResponseDTOList);
    }

    @GetMapping("/tags/basic")
    public ResponseDTO getTotalBasicTagReport(
            StatisticRequestDTO dto
    ){
        List<TagStatisticResponseDTO> tagStatisticResponseDTOList = statisticsService.getBasicTagStatistics(dto);
        return DataResponseDTO.of(tagStatisticResponseDTOList);
    }

    @GetMapping("/tags/{tagID}")
    public ResponseDTO getTagReport(
            StatisticRequestDTO dto,
            @PathVariable(value = "tagID") Long tagID
    ){
        TagStatisticResponseDTO tagStatisticResponseDTO = statisticsService.getTagStatistic(dto,tagID);
        return DataResponseDTO.of(tagStatisticResponseDTO);
    }

    @GetMapping("/excluded")
    public ResponseDTO getTagReport(
            StatisticRequestDTO dto
    ){
        ExcludedResponseDTO excludedResponseDTO = statisticsService.getExcludedStatistics(dto);
        return DataResponseDTO.of(excludedResponseDTO);
    }
}
