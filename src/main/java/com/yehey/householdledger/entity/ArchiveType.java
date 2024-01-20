package com.yehey.householdledger.entity;

import com.yehey.householdledger.dto.ArchiveTypeDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;

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

  @Column(name = "type_name", unique = true, nullable = false)
  private String name;

  //    @OneToMany(fetch = FetchType.LAZY)
//    private Set<Tag> tag;
  public ArchiveTypeDTO.CreateType toCreateDTO() {
    return ArchiveTypeDTO.CreateType.builder()
        .name(this.name)
        .build();
  }
}
