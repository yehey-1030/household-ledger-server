package com.yehey.householdledger.entity;

import com.yehey.householdledger.dto.TagDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archivetype_id")
    private ArchiveType archiveTypeID;

    @ManyToMany(mappedBy = "linkedTags")
    Set<Ledger> usedLedgers;

    @ManyToMany(mappedBy = "linkedTags")
    Set<Ledger> usedWithdrawal;

    public TagDto.Create ToCreateDto(){
        return TagDto.Create.builder()
//                .tagID(this.tagID)
                .name(this.name)
                .build();
    }
}
