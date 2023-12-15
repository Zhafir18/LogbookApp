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
}
