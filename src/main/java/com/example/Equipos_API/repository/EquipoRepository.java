package com.example.Equipos_API.repository;

import com.example.Equipos_API.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findAllByNombreContainingIgnoreCase(String nombre);
}
