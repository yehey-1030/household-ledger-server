package com.yehey.householdledger.dto.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TagResponseDTO {
    private Long tagID;
    private Long parentID;
    private Long archiveTypeID;
    private String name;
}