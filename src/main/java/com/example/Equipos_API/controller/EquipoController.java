package com.example.Equipos_API.controller;

import com.example.Equipos_API.dto.EquipoDTO;
import com.example.Equipos_API.entity.Equipo;
import com.example.Equipos_API.mapper.EquipoMapper;
import com.example.Equipos_API.service.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class EquipoController {

    final EquipoService equipoService;

    final EquipoMapper equipoMapper;

    @GetMapping
    public ResponseEntity<List<Equipo>> getAll(){
        return ResponseEntity.ok(equipoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getById(@PathVariable Long id){
        return ResponseEntity.ok(equipoService.getById(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> search(@RequestParam String nombre){
        return ResponseEntity.ok(equipoService.searchByNombre(nombre));
    }



    @PostMapping
    public ResponseEntity<Equipo> save(@RequestBody EquipoDTO dto){
        Equipo saved = equipoService.save(equipoMapper.toEntity(dto));
        return ResponseEntity.created(URI.create("/equipos/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> update(@RequestBody EquipoDTO dto, @PathVariable Long id){
        Equipo equipo = equipoMapper.toEntity(dto);
        equipo.setId(id);
        return ResponseEntity.ok(equipoService.save(equipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        equipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
