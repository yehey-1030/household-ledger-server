package com.yehey.householdledger.controller;

import com.yehey.householdledger.dto.response.DataResponseDTO;
import com.yehey.householdledger.dto.response.ResponseDTO;
import com.yehey.householdledger.dto.statistics.StatisticRequestDTO;
import com.yehey.householdledger.dto.statistics.TotalResponseDTO;
import com.yehey.householdledger.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
