package com.yehey.householdledger.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "archive_type")
@Builder
public class ArchiveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archivetype_id")
    private Long archiveTypeID;

    @Column(name="type_name")
    private String name;

}
