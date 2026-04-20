package com.example.Equipos_API.service;

import com.example.Equipos_API.entity.Equipo;
import com.example.Equipos_API.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    public final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository repository){
        this.equipoRepository = repository;
    }

    public List<Equipo> getAll(){
        return equipoRepository.findAll();
    }

    public Equipo getById(Long id){
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo not found"));
    }

    public List<Equipo> searchByNombre(String nombre){
        return equipoRepository.findAllByNombreContainingIgnoreCase(nombre);
    }

    public Equipo save(Equipo equipo){
        return equipoRepository.save(equipo);
    }

    public void delete(Equipo equipo){
        equipoRepository.delete(equipo);
    }
}
