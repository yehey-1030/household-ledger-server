package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.AutomaticWithdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepostiory extends JpaRepository<AutomaticWithdrawal,Long> {
}
