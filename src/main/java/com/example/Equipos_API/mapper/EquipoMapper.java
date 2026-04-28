package com.example.Equipos_API.mapper;

import com.example.Equipos_API.dto.EquipoDTO;
import com.example.Equipos_API.entity.Equipo;
import org.springframework.stereotype.Component;

@Component
public class EquipoMapper {

    public Equipo toEntity(EquipoDTO dto){
        Equipo equipo = new Equipo();
        equipo.setLiga(dto.getLiga());
        equipo.setNombre(dto.getNombre());
        equipo.setPais(dto.getPais());
        return equipo;
    }

    public EquipoDTO toDTO(Equipo equipo){
        EquipoDTO dto = new EquipoDTO();
        dto.setLiga(equipo.getLiga());
        dto.setPais(equipo.getPais());
        dto.setNombre(equipo.getNombre());
        return dto;
    }

}
