package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag,Long> {

    List<Tag> findAllByArchiveTypeID(ArchiveType archiveTypeID);
    Tag findByTagID(Long tagID);

    List<Tag> findAllByArchiveTypeIDAndParentID(ArchiveType archiveTypeId,Tag parentID);
    List<Tag> findAllByParentID(Tag parentID);
}
