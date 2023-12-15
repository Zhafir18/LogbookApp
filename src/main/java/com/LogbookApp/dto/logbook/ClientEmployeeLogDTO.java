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
}
