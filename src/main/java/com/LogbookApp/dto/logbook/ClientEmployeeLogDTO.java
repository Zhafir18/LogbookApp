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

public class ClientEmployeeLogDTO {
    private Long id;
    private String username;
    private LocalDate logDate;
    private Boolean clientApproval;
    private LocalDate clientApprovalDate;
    private String note;
    private String activity;
    private String dateTimeFormat;

    public ClientEmployeeLogDTO(Long id, String username, LocalDate logDate, Boolean clientApproval, LocalDate clientApprovalDate, String note, String activity) {
        this.id = id;
        this.username = username;
        this.logDate = logDate;
        this.clientApproval = clientApproval;
        this.clientApprovalDate = clientApprovalDate;
        this.note = note;
        this.activity = activity;
    }
}
