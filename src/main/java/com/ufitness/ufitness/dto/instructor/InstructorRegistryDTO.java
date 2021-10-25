package com.ufitness.ufitness.dto.instructor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRegistryDTO {
    @NotNull(message = "O campo nome deve ser preenchido")
    @NotBlank(message = "O campo nome deve ser preenchido")
    @NotEmpty(message = "O campo nome deve ser preenchido")
    @Size(max = 255, message = "O campo não deve ter mais de 255 caracteres.")
    private String name;
    @NotNull(message = "O campo email deve ser preenchido")
    @NotBlank(message = "O campo email deve ser preenchido")
    @Email(regexp = "^(.+)@(.+)$", message = "Email inválido")
    private String email;
    @NotNull(message = "O campo senha deve ser preenchido")
    @NotBlank(message = "O campo senha deve ser preenchido")
    @Size(min = 8, max = 255, message = "O campo de senha deve ter no mínimo 8 caracteres")
    private String password;
    @NotNull(message = "O campo de cpf deve ser preenchido")
    @NotBlank(message = "O campo de cpf deve ser preenchido")
    @Size(min = 11, max = 11, message = "O campo CPF deve conter 11 caracteres númericos")
    @Pattern(regexp = "\\d{3}\\d{3}\\d{3}\\d{2}", message = "Formato de CPF inválido")
    private String cpf;
    @NotNull(message = "O campo CREF deve ser preenchido")
    @NotBlank(message = "O campo CREF deve ser preenchido")
    @Size(min = 10, max = 10, message = "O campo CREF deve conter 10 caracteres númericos")
    @Pattern(regexp = "\\d{5}-G\\/[A-Z]{1,2}", message = "Formato de CREF inválido")
    private String cref;
}