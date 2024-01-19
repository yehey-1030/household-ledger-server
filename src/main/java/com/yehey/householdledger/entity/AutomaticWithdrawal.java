package com.yehey.householdledger.entity;

import com.yehey.householdledger.utils.converter.ExcludedAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;
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
    @Column(name="withdrawal_id")
    private Long withdrawalID;

    @Column(name = "cycle",nullable = false)
    private String cycle;

    @Column(name="amount",nullable = false)
    private Long amount;

    @Column(name = "title",nullable = false)
    private String title;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "archivetype_id")
    private ArchiveType archiveTypeID;

    @Column(name="memo")
    private String memo;

    @Column(name="is_excluded",nullable = false,columnDefinition = "TINYINT",length = 1)
    @Convert(converter = ExcludedAttributeConverter.class)
    @ColumnDefault("0")
    private Boolean isExcluded;

    @OneToMany(mappedBy = "withdrawal")
    List<WithdrawalTagRelation> relation;
}
