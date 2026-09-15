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


    @Operation(
            summary = "saves a new team",
            description = "Adds a new team to the database with the specified information",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Information of the football team to create",
                    required = true
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Team succesfully saved"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The object sent was incorrect"
            )
    })
    @PostMapping
    public ResponseEntity<Equipo> save(
            @Valid @RequestBody EquipoDTO dto){
        Equipo saved = equipoService.save(equipoMapper.toEntity(dto));
        return ResponseEntity.created(URI.create("/equipos/" + saved.getId())).body(saved);
    }

    @Operation(
            summary = "Updates a team",
            description = "Updates an existing team with new information",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "New information to update the designated team",
                    required = true
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Team updated correctly"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The object sent was incorrect"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Team not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> update(
            @Parameter(description = "A valid EquipoUpdateDTO object")
            @Valid @RequestBody EquipoUpdateDTO dto,
            @Parameter(description = "The unique identifier of a team")
            @PathVariable Long id){
        Equipo equipo = equipoService.getById(id);
        equipo = equipoMapper.toUpdatedEntity(dto, equipo);
        return ResponseEntity.ok(equipoService.save(equipo));
    }

    @Operation(
            summary = "Deletes a team",
            description = "Deletes a team with the given id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Team deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Team not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "The unique identifier of a team")
            @PathVariable Long id){
        equipoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
