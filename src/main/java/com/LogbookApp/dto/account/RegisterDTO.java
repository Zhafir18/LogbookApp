package com.LogbookApp.dto.account;

import com.LogbookApp.validation.ComparePassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ComparePassword(message = "Password tidak sama!")

public class RegisterDTO {
    @NotBlank(message = "Username harus diisi")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String username;

    @NotBlank(message = "Password harus diisi")
    @Size(min = 8, message = "Minimal 8 karakter")
    private String password;

    @NotBlank(message = "Confirm password harus diisi")
    @Size(min = 8, message = "Minimal 8 karakter")
    private String confirmPassword;

    @NotBlank(message = "Role harus diisi")
    private String role;

    @NotBlank(message = "Nama harus diisi")
    private String name;

    private LocalDate birthDate;
    private String position;
    private String company;
}
