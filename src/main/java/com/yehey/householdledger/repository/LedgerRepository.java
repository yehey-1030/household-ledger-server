package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.Ledger;
import com.yehey.householdledger.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {

  List<Ledger> getAllByDateBetweenOrderByDate(LocalDate start, LocalDate end);

  @Query(value = "select COALESCE(sum(l.amount),0) as total from Ledger l where l.archiveTypeID.archiveTypeID=:archiveTypeID and l.date>=:start and l.date<=:end")
  Long getTotalSumByDateAndArchiveTypeID(@Param(value = "archiveTypeID") Long archiveTypeID,
      @Param(value = "start") LocalDate start, @Param(value = "end") LocalDate end);

  @Query(value = "select coalesce(sum(l.amount),0) from Ledger l where l.date>=:start and l.date<=:end and l.archiveTypeID.archiveTypeID=:archiveTypeID and l.isExcluded=true")
  Long getExcludedTotalSum(@Param(value = "start") LocalDate start,
      @Param(value = "end") LocalDate end, @Param(value = "archiveTypeID") Long archiveTypeID);

  @Query(value = "select l.ledgerID from Ledger l where l.date>=:start and l.date<=:end and l.archiveTypeID.archiveTypeID=:archiveTypeID and l.isExcluded=true order by l.date,l.ledgerID")
  List<Long> getExcludedLedgerList(@Param(value = "start") LocalDate start,
      @Param(value = "end") LocalDate end, @Param(value = "archiveTypeID") Long archiveTypeID);

}
