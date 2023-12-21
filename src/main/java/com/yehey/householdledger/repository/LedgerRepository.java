package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger,Long> {
    List<Ledger> getAllByDateBetweenOrderByDate(LocalDate start, LocalDate end);

    @Query(value = "select sum(l.amount) as total from Ledger l where l.archiveTypeID.archiveTypeID=:archiveTypeID and l.date>:start and l.date<:end")
    Long getTotalSumByDateAndArchiveTypeID(@Param(value = "archiveTypeID")Long archiveTypeID,@Param(value="start") LocalDate start, @Param(value = "end") LocalDate end);
}
