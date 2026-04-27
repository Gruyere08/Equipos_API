package com.example.Equipos_API.controller;

import com.example.Equipos_API.entity.Equipo;
import com.example.Equipos_API.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class EquipoController {

    final EquipoService equipoService;

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

    @PostMapping
    public Equipo save(@RequestBody Equipo equipo){
        return equipoService.save(equipo);
    }

    @PutMapping("/{id}")
    public Equipo update(@RequestBody Equipo equipo, @PathVariable Long id){
        equipo.setId(id);
        return equipoService.save(equipo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        equipoService.deleteById(id);
    }

}
