package com.yehey.householdledger.entity;

import com.yehey.householdledger.dto.TagDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tag")
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagID;

    @Column(name = "tag_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Tag parentID;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentID")
    private List<Tag> children;


    public TagDto.Create ToCreateDto(){
        return TagDto.Create.builder()
//                .tagID(this.tagID)
                .name(this.name)
                .build();
    }
}
