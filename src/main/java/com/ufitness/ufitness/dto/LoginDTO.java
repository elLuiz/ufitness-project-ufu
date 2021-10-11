package com.ufitness.ufitness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotNull(message = "O campo nome deve ser preenchido")
    @NotBlank(message = "O campo nome deve ser preenchido")
    @Email(regexp = "^(.+)@(.+)$", message = "Email inválido")
    private String email;
    @NotNull(message = "O campo nome deve ser preenchido")
    @NotBlank(message = "O campo nome deve ser preenchido")
    @Size(min = 8, max = 255, message = "O campo de senha deve ter no mínimo 8 caracteres")
    private String password;
}
