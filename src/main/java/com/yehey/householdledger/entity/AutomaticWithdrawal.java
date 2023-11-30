package com.yehey.householdledger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "automatic_withdrawal")
@Builder
public class AutomaticWithdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long withdrawalID;

    @Column(name = "target_date")
    private LocalDate targetDate;

    @Column(name="amount")
    private Long amount;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archivetype_id")
    private ArchiveType archiveTypeID;

    @ManyToMany
    @JoinTable(name = "withdrawal_tag_relation",
            joinColumns = @JoinColumn(name = "withdrawal_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id"))
    Set<Tag> linkedTags;
}
