package com.yehey.householdledger.controller;

import com.yehey.householdledger.dto.ArchiveTypeDTO;
import com.yehey.householdledger.dto.response.DataResponseDTO;
import com.yehey.householdledger.dto.response.ResponseDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.service.ArchiveTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/archive-types")
@Slf4j
public class ArchiveTagController {

  private final ArchiveTypeService service;

  @Autowired
  public ArchiveTagController(ArchiveTypeService service) {
    this.service = service;
  }

  @PostMapping("/type")
  public ArchiveTypeDTO.CreateType postArchiveType(
      @RequestBody ArchiveTypeDTO.CreateType dto
  ) {
    log.warn(dto.toString());
    return this.service.CreateType(dto);
  }

  @GetMapping("")
  public ResponseDTO getAllArchiveTypes() {
    List<ArchiveType> typeList = service.getAllType();
    return DataResponseDTO.of(typeList, "get all types success");
  }
}
