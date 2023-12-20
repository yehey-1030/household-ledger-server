package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LedgerRepository extends JpaRepository<Ledger,Long> {
    List<Ledger> getAllByDateBetweenOrderByDate(LocalDate start, LocalDate end);

}
