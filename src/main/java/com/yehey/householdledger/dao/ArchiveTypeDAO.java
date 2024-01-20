package com.yehey.householdledger.dao;

import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArchiveTypeDAO {

  private final ArchiveTypeRepository repository;

  @Autowired
  public ArchiveTypeDAO(ArchiveTypeRepository repository) {
    this.repository = repository;
  }

  public ArchiveType CreateType(ArchiveType entity) {
    return this.repository.save(entity);
  }
}
