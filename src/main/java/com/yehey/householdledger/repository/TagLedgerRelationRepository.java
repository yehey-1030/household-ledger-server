package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.TagLedgerRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagLedgerRelationRepository  extends JpaRepository<TagLedgerRelation,Long> {
}
