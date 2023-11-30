package com.yehey.householdledger.service;

import com.yehey.householdledger.dao.ArchiveTypeDAO;
import com.yehey.householdledger.dto.ArchiveTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchiveTypeService {
    private final ArchiveTypeDAO dao;

    @Autowired
    public ArchiveTypeService(ArchiveTypeDAO dao){
        this.dao = dao;
    }

    public ArchiveTypeDTO.CreateType CreateType(ArchiveTypeDTO.CreateType dto){
        return this.dao.CreateType(dto.ToEntity()).toCreateDTO();
    }
}
