package com.example.Equipos_API.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Schema(description = "A data transfer object meant for updating teams. It accepts missing fields")
public class EquipoUpdateDTO {
    @Schema(description = "Name of the team", example = "Real Madrid")
    @Size(min = 2, max = 255)
    String nombre;
    @Schema(description = "League the team belongs to", example = "La liga")
    String liga;
    @Schema(description = "The team's country of origin", example = "España")
    String pais;
}
