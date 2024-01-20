package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.WithdrawalTagRelation;
import com.yehey.householdledger.utils.WithdrawalRelationKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalTagRelataionRepository extends
    JpaRepository<WithdrawalTagRelation, WithdrawalRelationKey> {

}
