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

public class EditLogDTO {
    private Long id;
    private String username;
    private String note;
    private Boolean clientApproval;
    private Boolean hrdApproval;
    private String clientUsername;
    private String hrdUsername;
    private LocalDate submissionDate;
    private String activity;
    private LocalDate logDate;
}
