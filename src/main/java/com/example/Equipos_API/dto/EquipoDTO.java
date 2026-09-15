package com.example.Equipos_API.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data transfer object for teams")
public class EquipoDTO {
    @Schema(description = "Name of the team", example = "Real Madrid")
    @NotBlank(message = "Se requiere un nombre")
    @Size(min = 2, max = 255)
    String nombre;
    @Schema(description = "League the team belongs to", example = "La liga")
    @NotBlank(message = "Se requiere una liga")
    String liga;
    @Schema(description = "The team's country of origin", example = "España")
    @NotBlank(message = "Se requiere un pais")
    String pais;
}
