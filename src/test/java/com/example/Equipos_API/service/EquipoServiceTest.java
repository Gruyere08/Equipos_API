package com.example.Equipos_API.service;

import com.example.Equipos_API.entity.Equipo;
import com.example.Equipos_API.exception.EquipoNotFoundException;
import com.example.Equipos_API.repository.EquipoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipoServiceTest {

    // Create mock
    @Mock
    private EquipoRepository repository;

    // Inject mocks into the class to be tested
    @InjectMocks
    private EquipoService service;

    @Test
    void shouldReturnAllEquipos() {

        // Arrange
        List<Equipo> equipos = List.of(
                new Equipo(1L, "Barcelona", "La Liga", "España"),
                new Equipo(2L, "Liverpool", "Premier League", "Inglaterra")
        );

        when(repository.findAll()).thenReturn(equipos);

        // Act
        List<Equipo> resultado = service.getAll();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Barcelona", resultado.get(0).getNombre());
        assertEquals("Liverpool", resultado.get(1).getNombre());

        // Verifies the repository was called correctly
        verify(repository).findAll();
    }

    @Test
    void shouldReturnEquipoWhenIdExists(){

        Long id = 1L;
        //Arrange
        Equipo equipo = new Equipo(
                id, "Barcelona", "La liga", "España");
        when(repository.findById(id)).thenReturn(Optional.of(equipo));

        //Act
        Equipo resultado = service.getById(id);

        //Assert
        assertSame(equipo, resultado);

        //Verify
        verify(repository).findById(id);


    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist(){
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        //Assert (The method is expected to throw an exception before it can return a value, so the Act is performed inside assertThrows().)
        assertThrows(EquipoNotFoundException.class, ()-> service.getById(id));
        //verify (optional in this case)
        verify(repository).findById(id);

    }

    @Test
    void shouldReturnEquiposMatchingName(){
        //Arrange
        String name = "Barcelona";
        List<Equipo> equipos = List.of(new Equipo(1L,name,"La liga","España"));
        when(repository.findAllByNombreContainingIgnoreCase(name)).thenReturn(equipos);
        //act
        List<Equipo> resultado = service.searchByNombre(name);
        //Assert

        assertSame(equipos, resultado);

        verify(repository).findAllByNombreContainingIgnoreCase(name);
    }

    @Test
    void shouldSaveEquipoAndReturnTheSavedEquipo(){
        //Arrange
        Equipo equipo = new Equipo(null, "Barcelona", "La liga", "España");
        Equipo saved = new Equipo(1L, "Barcelona", "La liga", "España");

        when(repository.save(equipo)).thenReturn(saved);

        //Act
        Equipo result = service.save(equipo);

        //Assert
        assertSame(saved, result);

        //verify
        verify(repository).save(equipo);
    }

    @Test
    void shouldDeleteEquipoWithTheSpecifiedId(){
        Long id = 1L;
        //Arrange
        Equipo equipo = new Equipo(id, "Barcelona", "La liga", "España");
        when(repository.findById(id)).thenReturn(Optional.of(equipo));
        doNothing().when(repository).delete(equipo);
        //act
        service.deleteById(id);
        //verify
        verify(repository).findById(id);
        verify(repository).delete(equipo);

    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingEquipo(){
        Long id = 1L;
        //Arrange
        when(repository.findById(id)).thenReturn(Optional.empty());
        //Assert/Act
        assertThrows(EquipoNotFoundException.class, ()-> service.deleteById(id));
        //verify
        verify(repository).findById(id);
        verify(repository, never()).delete(any());
    }


}

