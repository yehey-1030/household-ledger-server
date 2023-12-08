package com.yehey.householdledger.service;

import com.yehey.householdledger.dao.TagDAO;
import com.yehey.householdledger.dto.tag.TagResponseDTO;
import com.yehey.householdledger.dto.tag.PostTagRequestDTO;
import com.yehey.householdledger.entity.ArchiveType;
import com.yehey.householdledger.entity.Tag;
import com.yehey.householdledger.repository.ArchiveTypeRepository;
import com.yehey.householdledger.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<TagResponseDTO> getRootTagsByTypeID(Long typeID){
        ArchiveType archiveType = archiveTypeRepository.findByArchiveTypeID(typeID);

        List<Tag> tagList= tagRepository.findAllByArchiveTypeIDAndParentID(archiveType,null);
        List<TagResponseDTO> tagResponseDTOS = new ArrayList<>();
        for (Tag tag: tagList){
            TagResponseDTO dto = TagResponseDTO.builder()
                    .archiveTypeID(tag.getArchiveTypeID().getArchiveTypeID())
                    .name(tag.getName())
                    .tagID(tag.getTagID())
                    .build();

//            if(tag.getParentID()!=null){
//                dto.setParentID(tag.getParentID().getTagID());
//            }

            tagResponseDTOS.add(dto);
        }
        return tagResponseDTOS;
    }

    public List<TagResponseDTO> getChildTagsByParentID(Long parentID){
        Tag parent = tagRepository.findByTagID(parentID);

        List<Tag> tagList = tagRepository.findAllByParentID(parent);
        List<TagResponseDTO> tagResponseDTOS = new ArrayList<>();

        for (Tag tag:tagList){
            TagResponseDTO dto = TagResponseDTO.builder()
                    .archiveTypeID(tag.getArchiveTypeID().getArchiveTypeID())
                    .name(tag.getName())
                    .tagID(tag.getTagID())
                    .parentID(tag.getParentID().getTagID())
                    .build();

            tagResponseDTOS.add(dto);
        }

        return tagResponseDTOS;
    }
}
