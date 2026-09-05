package com.example.Equipos_API.controller;

import com.example.Equipos_API.dto.EquipoDTO;
import com.example.Equipos_API.dto.EquipoUpdateDTO;
import com.example.Equipos_API.entity.Equipo;
import com.example.Equipos_API.mapper.EquipoMapper;
import com.example.Equipos_API.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
@Tag(
        name = "Equipos",
        description = "Operations related to football teams"
)
public class EquipoController {

    final EquipoService equipoService;

    final EquipoMapper equipoMapper;

    @GetMapping
    @Operation(
            summary = "Get all teams",
            description = "Returns all football teams registered in the system"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Teams successfully retrieved"
    )
    public ResponseEntity<List<Equipo>> getAll(){
        return ResponseEntity.ok(equipoService.getAll());
    }

    @Operation(
            summary = "Gets a specific team",
            description = "Gets a team based on its unique identifier"

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Team succesfully found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Team not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getById(
            @Parameter(description = "The unique identifier of a team")
            @PathVariable Long id)
    {
        return ResponseEntity.ok(equipoService.getById(id));
    }

    @Operation(
            summary = "Searches teams",
            description = "Searches a list of team that matches a certain given name"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Teams succesfully retrieved"
    )
    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> search(
            @Parameter(description = "A name to be searched")
            @RequestParam String nombre){
        return ResponseEntity.ok(equipoService.searchByNombre(nombre));
    }



    @PostMapping
    public ResponseEntity<Equipo> save(@Valid @RequestBody EquipoDTO dto){
        Equipo saved = equipoService.save(equipoMapper.toEntity(dto));
        return ResponseEntity.created(URI.create("/equipos/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> update(@Valid @RequestBody EquipoUpdateDTO dto, @PathVariable Long id){
        Equipo equipo = equipoService.getById(id);
        equipo = equipoMapper.toUpdatedEntity(dto, equipo);
        return ResponseEntity.ok(equipoService.save(equipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        equipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
