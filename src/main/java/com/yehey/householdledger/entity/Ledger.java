package com.yehey.householdledger.entity;

import com.yehey.householdledger.utils.converter.ExcludedAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

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

  @Column(name = "date", nullable = false)
  private LocalDate date;

  @Column(name = "amount", nullable = false)
  private Long amount;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "memo")
  private String memo;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "archivetype_id")
  private ArchiveType archiveTypeID;

  @OneToMany(mappedBy = "ledger")
  List<TagLedgerRelation> relation;

  @Column(name = "is_excluded", nullable = false, columnDefinition = "TINYINT", length = 1)
  @Convert(converter = ExcludedAttributeConverter.class)
  @ColumnDefault("0")
  private Boolean isExcluded;

}
