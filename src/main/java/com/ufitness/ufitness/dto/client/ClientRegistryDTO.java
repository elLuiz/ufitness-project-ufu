package com.ufitness.ufitness.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegistryDTO {
    @NotNull(message = "O campo nome deve ser preenchido")
    @NotBlank(message = "O campo nome deve ser preenchido")
    @NotEmpty(message = "O campo nome deve ser preenchido")
    @Size(max = 255, message = "O campo não deve ter mais de 255 caracteres.")
    @Pattern(regexp = "[a-zA-Z'\\p{Punct}\\s]+", message = "O campo nome possui caracteres inválidos.")
    private String name;
    @NotNull(message = "O campo nome deve ser preenchido")
    @NotBlank(message = "O campo nome deve ser preenchido")
    @Email(regexp = "^(.+)@(.+)$", message = "Email inválido")
    private String email;
    @NotNull(message = "O campo nome deve ser preenchido")
    @NotBlank(message = "O campo nome deve ser preenchido")
    @Size(min = 8, max = 255, message = "O campo de senha deve ter no mínimo 8 caracteres")
    private String password;
}