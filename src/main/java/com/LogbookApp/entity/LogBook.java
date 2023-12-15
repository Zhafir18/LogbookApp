package com.LogbookApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "LogBook")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LogBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Username", insertable = false, updatable = false)
    private Account account;

    @Column(name = "Username")
    private String username;

    @Column(name = "Activity")
    private String activity;

    @Column(name = "Note")
    private String note;

    @Column(name = "LogDate")
    private LocalDate logDate;

    @Column(name = "ClientApproval")
    private Boolean clientApproval;

    @Column(name = "ClientApprovalDate")
    private LocalDate clientApprovalDate;

    @Column(name = "HRDApproval")
    private Boolean hrdApproval;

    @Column(name = "HRDApprovalDate")
    private LocalDate hrdApprovalDate;

    @ManyToOne
    @JoinColumn(name = "ClientUsername", insertable = false, updatable = false)
    private Account clientAccount;

    @Column(name = "ClientUsername")
    private String clientUsername;

    @ManyToOne
    @JoinColumn(name = "HrdUsername", insertable = false, updatable = false)
    private Account hrdAccount;

    @Column(name = "HRDUsername")
    private String hrdUsername;

    @Column(name = "SubmissionDate")
    private LocalDate submissionDate;

    @Column(name = "Period")
    private Integer period;
}
