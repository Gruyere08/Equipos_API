package com.example.Equipos_API.controller;

import com.example.Equipos_API.entity.Equipo;
import com.example.Equipos_API.service.EquipoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

    final EquipoService equipoService;

    public EquipoController(EquipoService equipoService){
        this.equipoService = equipoService;
    }

    @GetMapping
    public List<Equipo> getAll(){
        return equipoService.getAll();
    }

    @GetMapping("/{id}")
    public Equipo getById(@PathVariable Long id){
        return equipoService.getById(id);
    }

    @GetMapping("/buscar")
    public List<Equipo> search(@RequestParam String nombre){
        return equipoService.searchByNombre(nombre);
    }


}
