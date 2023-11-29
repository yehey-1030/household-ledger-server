package com.yehey.householdledger.service;

import com.yehey.householdledger.dao.TagDao;
import com.yehey.householdledger.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private final TagDao dao;

    @Autowired
    public TagService(TagDao dao) {
        this.dao = dao;
    }

    public TagDto.Create Create(TagDto.Create dto){
        return this.dao.Create(dto.ToEntity()).ToCreateDto();
    }

}
