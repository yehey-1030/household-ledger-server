package com.yehey.householdledger.service;

import com.yehey.householdledger.dao.TagDAO;
import com.yehey.householdledger.dto.tag.PostTagRequestDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.Tag;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagDAO dao;
    private final TagRepository tagRepository;
    private final ArchiveTypeRepository archiveTypeRepository;

//    @Autowired
//    public TagService(TagDAO dao) {
//        this.dao = dao;
//    }

    public Tag getTag(Long tagID){
        return tagRepository.findByTagID(tagID);
    }

    public void saveTagWithParent(PostTagRequestDTO dto){
        Tag parentTag = tagRepository.findByTagID(dto.getParentID());
        ArchiveType type = archiveTypeRepository.findByArchiveTypeID(dto.getArchiveTypeID());

        Tag tag = Tag.builder()
                .parentID(parentTag)
                .archiveTypeID(type)
                .name(dto.getName())
                .build();

        tagRepository.save(tag);

    }

    public void saveTagExceptParent(PostTagRequestDTO dto){
        ArchiveType type = archiveTypeRepository.findByArchiveTypeID(dto.getArchiveTypeID());

        Tag tag = Tag.builder()
                .archiveTypeID(type)
                .name(dto.getName())
                .build();

        tagRepository.save(tag);
    }

}
