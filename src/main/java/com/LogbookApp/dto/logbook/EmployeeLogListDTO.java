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
}
