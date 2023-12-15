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

public class InsertNewLogDTO {
    private Long id;
    private String username;
    private String clientUsername;
    private String hrdUsername;
    private Boolean clientApproval;
    private Boolean hrdApproval;
    private Integer period;
}
