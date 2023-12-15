package com.LogbookApp.dto.account;

import com.LogbookApp.validation.CheckAuthentication;
import com.LogbookApp.validation.ComparePassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@CheckAuthentication(message = "Old password salah")
@ComparePassword(message = "Password tidak sama!")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChangePasswordDTO {
    private String username;

    @NotBlank(message = "Harus diisi")
    @Size(min = 8, message = "minimal 8 karakter")
    private String oldPassword;

    @NotBlank(message = "Harus diisi")
    @Size(min = 8, message = "Minimal 8 Karakter")
    private String password;

    @NotBlank(message = "Harus diisi")
    @Size(min = 8, message = "Minimal 8 karakter")
    private String confirmPassword;
}
