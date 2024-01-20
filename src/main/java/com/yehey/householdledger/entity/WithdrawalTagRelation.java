package com.yehey.householdledger.entity;

import com.yehey.householdledger.utils.WithdrawalRelationKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "withdrawal_tag_relation")
@Builder
public class WithdrawalTagRelation {

  @EmbeddedId
  private WithdrawalRelationKey withdrawalRelationKey;

  @MapsId("tagID")
  @ManyToOne
  @JoinColumn(name = "tag_id")
  private Tag tag;

  @MapsId("withdrawalID")
  @ManyToOne
  @JoinColumn(name = "withdrawal_id")
  private AutomaticWithdrawal withdrawal;

}
