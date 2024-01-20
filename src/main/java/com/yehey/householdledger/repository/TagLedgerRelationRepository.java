package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.TagLedgerRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TagLedgerRelationRepository extends JpaRepository<TagLedgerRelation, Long> {

  @Query(value = "select COALESCE(sum(r.ledger.amount),0) from TagLedgerRelation r where r.tag.tagID=:tagID and r.ledger.date>=:start and r.ledger.date<=:end and r.ledger.archiveTypeID.archiveTypeID=:archiveTypeID and r.ledger.isExcluded=false")
  Long getTotalSumByTagAndDate(@Param(value = "tagID") Long tagID,
      @Param(value = "start") LocalDate start, @Param(value = "end") LocalDate end,
      @Param(value = "archiveTypeID") Long archiveTypeID);

  @Query(value = "select r.ledger.ledgerID from TagLedgerRelation r where r.tag.tagID=:tagID and r.ledger.date>=:start and r.ledger.date<=:end and r.ledger.archiveTypeID.archiveTypeID=:archiveTypeID and r.ledger.isExcluded=false order by r.ledger.date,r.ledger.ledgerID")
  List<Long> getLedgerListByTagAndDate(@Param(value = "tagID") Long tagID,
      @Param(value = "start") LocalDate start, @Param(value = "end") LocalDate end,
      @Param(value = "archiveTypeID") Long archiveTypeID);

}
