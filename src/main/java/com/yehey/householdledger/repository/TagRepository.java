package com.yehey.householdledger.repository;

import com.yehey.householdledger.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {

    List<Tag> findAllByTagID(Long tagID);
    Tag findByTagID(Long tagID);

    //parent tag를 가지는 children tag 전부 출력
//    List<Tag> findAllByParentID(Long parentID);

    // type id를 기반으로 최상위 태그들 전부 출력
//    List<Tag> findAllByArchiveTypeIDAndParentIDIsNull(Long typeID);

}
