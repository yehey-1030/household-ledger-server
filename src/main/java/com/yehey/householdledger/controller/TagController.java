package com.yehey.householdledger.controller;

import com.yehey.householdledger.dto.tag.PostTagRequestDTO;
import com.yehey.householdledger.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
@Slf4j
public class TagController {
    private final TagService service;

    @Autowired
    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping("/tags")
    public ResponseEntity<?> postTagWithParent(
            @RequestBody PostTagRequestDTO dto
            ){
        if (dto.getParentID()!=null){
            service.saveTagWithParent(dto);
        }else{
            service.saveTagExceptParent(dto);
        }
        return ResponseEntity.status(201).body("success");
    }
}
