package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.ArchiveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveTypeRepository extends JpaRepository<ArchiveType,Long> {
    ArchiveType findByArchiveTypeID(Long typeID);
}
