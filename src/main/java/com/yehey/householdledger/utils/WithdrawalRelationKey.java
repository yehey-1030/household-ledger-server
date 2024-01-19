package com.yehey.householdledger.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class WithdrawalRelationKey implements Serializable {
    private Long tagID;
    private Long withdrawalID;
}
