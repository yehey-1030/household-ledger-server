package com.yehey.householdledger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yehey.householdledger.entity.ArchiveType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ArchiveTypeDTO {

  @Getter
  @ToString
  @Builder
  public static class CreateType {

    @JsonProperty("type_name")
    private String name;

    public CreateType(String name) {
      this.name = name;
    }

    public CreateType() {
    }

    public ArchiveType ToEntity() {
      return ArchiveType.builder()
          .name(this.name)
          .build();
    }
  }
}
