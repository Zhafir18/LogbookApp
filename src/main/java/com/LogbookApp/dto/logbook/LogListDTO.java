package com.LogbookApp.dto.logbook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LogListDTO {
    private Long id;
    private String username;
    private LocalDate logDate;
    private Boolean clientApproval;
    private LocalDate clientApprovalDate;
    private Boolean hrdApproval;
    private LocalDate hrdApprovalDate;
    private String clientName;
    private String hrdName;
    private String note;
    private String activity;
    private String dateTimeFormat;

    public LogListDTO(Long id, String username, LocalDate logDate, Boolean clientApproval, LocalDate clientApprovalDate, Boolean hrdApproval, LocalDate hrdApprovalDate, String clientName, String hrdName, String note, String activity) {
        this.id = id;
        this.username = username;
        this.logDate = logDate;
        this.clientApproval = clientApproval;
        this.clientApprovalDate = clientApprovalDate;
        this.hrdApproval = hrdApproval;
        this.hrdApprovalDate = hrdApprovalDate;
        this.clientName = clientName;
        this.hrdName = hrdName;
        this.note = note;
        this.activity = activity;
    }
}
