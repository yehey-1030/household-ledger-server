package com.yehey.householdledger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yehey.householdledger.entity.Tag;
import lombok.*;

public class TagDto {
    @Getter
    @Builder
    @ToString
    public static class Create{
        @JsonProperty("id")
        private Long tagID;

        @JsonProperty("tag_name")
        private String name;

        public Tag ToEntity(){
            return Tag.builder()
//                    .tagID(this.tagID)
                    .name(this.name)
                    .build();
        }
    }

}
