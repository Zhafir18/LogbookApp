package com.LogbookApp.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClientEmployeeListDTO {
    private String username;
    private String name;
    private String position;
}
