package com.LogbookApp.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProfileDTO {
    private String username;
    private String name;
    private LocalDate birthDate;
    private String position;
    private String company;
    private String role;
}
