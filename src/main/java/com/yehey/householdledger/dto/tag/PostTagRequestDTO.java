package com.yehey.householdledger.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostTagRequestDTO {
    private Long archiveTypeID;
    private String name;
    private Long parentID;
}
