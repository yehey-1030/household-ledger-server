package com.yehey.householdledger.controller;

import com.yehey.householdledger.dto.response.DataResponseDTO;
import com.yehey.householdledger.dto.response.ResponseDTO;
import com.yehey.householdledger.dto.tag.TagResponseDTO;
import com.yehey.householdledger.dto.tag.PostTagRequestDTO;
import com.yehey.householdledger.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/archive-types/{typeID}")
    public ResponseDTO getAllTypeRootTags(
            @PathVariable(name = "typeID") Long typeID ){
        List<TagResponseDTO> tagList = service.getRootTagsByTypeID(typeID);

        return DataResponseDTO.of(tagList,"get type's root tag success");
    }

    @GetMapping("/tags/{parentTagID}")
    public ResponseDTO getAllChildTags(
            @PathVariable(name="parentTagID") Long parentTagID
    ){
        List<TagResponseDTO> tagList = service.getChildTagsByParentID(parentTagID);
        return DataResponseDTO.of(tagList,"get parent's child tags success");

    }
}
