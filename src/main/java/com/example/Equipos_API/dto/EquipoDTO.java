package com.example.Equipos_API.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EquipoDTO {
    @NotBlank(message = "Se requiere un nombre")
    @Size(min = 2, max = 255)
    String nombre;
    @NotBlank(message = "Se requiere una liga")
    String liga;
    @NotBlank(message = "Se requiere un pais")
    String pais;
}
