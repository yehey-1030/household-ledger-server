package com.yehey.householdledger.service;

import com.yehey.householdledger.dao.ArchiveTypeDAO;
import com.yehey.householdledger.dto.ArchiveTypeDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchiveTypeService {

  private final ArchiveTypeDAO dao;
  private final ArchiveTypeRepository archiveTypeRepository;

  public ArchiveTypeDTO.CreateType CreateType(ArchiveTypeDTO.CreateType dto) {
    return this.dao.CreateType(dto.ToEntity()).toCreateDTO();
  }

  public List<ArchiveType> getAllType() {
    return archiveTypeRepository.findAll();
  }
}
