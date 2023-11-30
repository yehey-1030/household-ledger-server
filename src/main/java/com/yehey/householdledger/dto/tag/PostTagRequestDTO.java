package com.yehey.householdledger.dto.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostTagRequestDTO {
    @JsonProperty("type_id")
    private Long archiveTypeID;

    @JsonProperty("name")
    private String name;

    @JsonProperty("parent_id")
    private Long parentID;
}
