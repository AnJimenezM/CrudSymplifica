package com.crud.backendcrud;
import com.crud.backendcrud.entity.Empleado;
import com.crud.backendcrud.repository.EmpleadoRepository;
import com.crud.backendcrud.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class EmpleadoTest {
    @Mock
    private EmpleadoRepository repository;

    @InjectMocks
    private EmpleadoServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarPorId_DeberiaRetornarEmpleado() {

        Empleado empleado = new Empleado();
        empleado.setId_empleado(1);
        empleado.setNombre("Jorge");

        when(repository.findById(1))
                .thenReturn(Optional.of(empleado));

        Empleado resultado = service.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Jorge", resultado.getNombre());

        verify(repository).findById(1);
    }
    @Test
    void guardar_DeberiaGuardarEmpleado() {

        Empleado empleado = new Empleado();
        empleado.setNombre("Luis");
        empleado.setApellido("Gomez");
        empleado.setCorreo("luis7812@gmail.com");
        empleado.setCiudad("Cundinamarca");
        empleado.setPosicion("ingeniero de mecatronica");

        when(repository.save(empleado))
                .thenReturn(empleado);

        Empleado resultado = service.guardar(empleado);

        assertNotNull(resultado);
        assertEquals("Luis", resultado.getNombre());

        verify(repository).save(empleado);

    }

    @Test
    void actualizar_DeberiaActualizarEmpleado() {

        // Arrange
        Empleado existente = new Empleado();
        existente.setId_empleado(1);
        existente.setNombre("Luis");

        Empleado actualizado = new Empleado();
        actualizado.setNombre("Miguel");
        actualizado.setApellido("Nuñez");
        actualizado.setCorreo("miguel87@gmail.com");
        actualizado.setCiudad("Antioquia");
        actualizado.setPosicion("Ingeniero de sistemas");

        when(repository.findById(1))
                .thenReturn(Optional.of(existente));

        when(repository.save(any(Empleado.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Empleado resultado = service.actualizar(1, actualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Miguel", resultado.getNombre());
        assertEquals("Nuñez", resultado.getApellido());
        assertEquals("miguel87@gmail.com", resultado.getCorreo());
        assertEquals("Antioquia", resultado.getCiudad());
        assertEquals("Ingeniero de sistemas", resultado.getPosicion());

        verify(repository).findById(1);
        verify(repository).save(any(Empleado.class));
    }
    @Test
    void eliminar_DeberiaEliminarEmpleado() {

        service.eliminar(1);

        verify(repository).deleteById(1);

    }

}
