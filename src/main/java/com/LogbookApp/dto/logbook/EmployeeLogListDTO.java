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

public class EmployeeLogListDTO {
    private Long id;
    private String username;
    private LocalDate logDate;
    private Boolean clientApproval;
    private Boolean hrdApproval;
    private String clientName;
    private String hrdName;
    private String activity;
    private String note;
    private String dateTimeFormat;

    public EmployeeLogListDTO(Long id, String username, LocalDate logDate, Boolean clientApproval, Boolean hrdApproval, String clientName, String hrdName, String activity, String note) {
        this.id = id;
        this.username = username;
        this.logDate = logDate;
        this.clientApproval = clientApproval;
        this.hrdApproval = hrdApproval;
        this.clientName = clientName;
        this.hrdName = hrdName;
        this.activity = activity;
        this.note = note;
    }
}
