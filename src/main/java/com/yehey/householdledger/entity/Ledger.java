package com.yehey.householdledger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
}
