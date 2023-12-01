package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<Ledger,Long> {

}
