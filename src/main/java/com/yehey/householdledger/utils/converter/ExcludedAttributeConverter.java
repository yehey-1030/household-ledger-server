package com.yehey.householdledger.utils.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ExcludedAttributeConverter implements AttributeConverter<Boolean, Integer> {

  @Override
  public Integer convertToDatabaseColumn(Boolean attribute) {
    if (attribute) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public Boolean convertToEntityAttribute(Integer dbData) {
    return dbData != 0;
  }
}
