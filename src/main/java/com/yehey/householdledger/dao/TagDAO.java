package com.yehey.householdledger.dao;

import com.yehey.householdledger.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yehey.householdledger.entity.Tag;

@Service
@Slf4j
public class TagDao {
    private final TagRepository repository;

    @Autowired
    public TagDao(TagRepository repository) {
        this.repository = repository;
    }

    public Tag Create(Tag entity){
        log.warn(entity.toString());
        return this.repository.save(entity);
    }
}