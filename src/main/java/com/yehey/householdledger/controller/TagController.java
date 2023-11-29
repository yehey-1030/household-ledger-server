package com.yehey.householdledger.controller;

import com.yehey.householdledger.dto.TagDto;
import com.yehey.householdledger.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/tags")
@Slf4j
public class TagController {
    private final TagService service;

    @Autowired
    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping("/tag")
    public TagDto.Create PostTag(
            @RequestBody TagDto.Create dto
    ){
        log.warn(dto.toString());
        return this.service.Create(dto);
    }
}
