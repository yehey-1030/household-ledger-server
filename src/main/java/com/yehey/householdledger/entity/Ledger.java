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
@Table(name = "ledger")
@Builder
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private Long ledgerID;

    @Column(name="date")
    private LocalDate date;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archivetype_id")
    private ArchiveType archiveTypeID;

    @ManyToMany
    @JoinTable(name = "ledger_tag_relation",
    joinColumns = @JoinColumn(name = "ledger_id"),
    inverseJoinColumns = @JoinColumn(name="tag_id"))
    Set<Tag> linkedTags;

}
