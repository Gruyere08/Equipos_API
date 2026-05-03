package com.example.Equipos_API.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class EquipoUpdateDTO {
    @Size(min = 2, max = 255)
    String nombre;
    String liga;
    String pais;
}
