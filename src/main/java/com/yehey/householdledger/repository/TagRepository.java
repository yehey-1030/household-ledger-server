package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
