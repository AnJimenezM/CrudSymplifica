package com.crud.backendcrud;
import com.crud.backendcrud.controller.EmpleadoController;
import com.crud.backendcrud.entity.Empleado;
import com.crud.backendcrud.service.ApiOpenStreetMap;
import com.crud.backendcrud.service.EmpleadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest(EmpleadoController.class)
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpleadoService empleadoService;

    @MockitoBean
    private ApiOpenStreetMap apiOpenStreetMap;

    @Test
    void listarEmpleados() throws Exception {

        Empleado empleado1 = new Empleado();
        empleado1.setId_empleado(1);
        empleado1.setNombre("Luis");
        empleado1.setApellido("Pérez");
        empleado1.setCorreo("luis@gmail.com");
        empleado1.setCiudad("Pereira");
        empleado1.setPosicion("Ingeniero");

        Empleado empleado2 = new Empleado();
        empleado2.setId_empleado(2);
        empleado2.setNombre("Ana");
        empleado2.setApellido("Gómez");
        empleado2.setCorreo("ana@gmail.com");
        empleado2.setCiudad("Medellín");
        empleado2.setPosicion("Analista");

        List<Empleado> empleados = List.of(empleado1, empleado2);

        when(empleadoService.listar()).thenReturn(empleados);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Luis"))
                .andExpect(jsonPath("$[1].nombre").value("Ana"));

        verify(empleadoService).listar();


    }

    @Test
    void ListarEmpleadosPorSuId() throws Exception {

        Empleado empleado = new Empleado();
        empleado.setId_empleado(1);
        empleado.setNombre("Luis");
        empleado.setApellido("Pérez");
        empleado.setCorreo("luis@gmail.com");
        empleado.setCiudad("Pereira");
        empleado.setPosicion("Ingeniero");

        when(empleadoService.buscarPorId(1)).thenReturn(empleado);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_empleado").value(1))
                .andExpect(jsonPath("$.nombre").value("Luis"))
                .andExpect(jsonPath("$.apellido").value("Pérez"))
                .andExpect(jsonPath("$.correo").value("luis@gmail.com"))
                .andExpect(jsonPath("$.ciudad").value("Pereira"))
                .andExpect(jsonPath("$.posicion").value("Ingeniero"));

        verify(empleadoService).buscarPorId(1);
    }


    @Test
    void GuardarEmpleado() throws Exception {

        Empleado empleado = new Empleado();
        empleado.setId_empleado(1);
        empleado.setNombre("Luis");
        empleado.setApellido("Pérez");
        empleado.setCorreo("luis@gmail.com");
        empleado.setCiudad("Pereira");
        empleado.setPosicion("Ingeniero");

        when(empleadoService.guardar(any(Empleado.class)))
                .thenReturn(empleado);

        JsonMapper mapper = JsonMapper.builder().build();

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(empleado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Luis"))
                .andExpect(jsonPath("$.apellido").value("Pérez"));

        verify(empleadoService).guardar(any(Empleado.class));
    }

    @Test
    void ActualizarEmpleado() throws Exception {

        Empleado empleado = new Empleado();
        empleado.setId_empleado(1);
        empleado.setNombre("Miguel");
        empleado.setApellido("Nuñez");
        empleado.setCorreo("miguel@gmail.com");
        empleado.setCiudad("Antioquia");
        empleado.setPosicion("Ingeniero");

        when(empleadoService.actualizar(any(Integer.class), any(Empleado.class)))
                .thenReturn(empleado);

        JsonMapper mapper = JsonMapper.builder().build();

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(empleado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_empleado").value(1))
                .andExpect(jsonPath("$.nombre").value("Miguel"))
                .andExpect(jsonPath("$.apellido").value("Nuñez"))
                .andExpect(jsonPath("$.correo").value("miguel@gmail.com"))
                .andExpect(jsonPath("$.ciudad").value("Antioquia"))
                .andExpect(jsonPath("$.posicion").value("Ingeniero"));

        verify(empleadoService).actualizar(any(Integer.class), any(Empleado.class));
    }

    @Test
    void EliminarEmpleado() throws Exception {

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());

        verify(empleadoService).eliminar(1);
    }

    @Test
    void obtenerUbicacion() throws Exception {

        when(apiOpenStreetMap.buscarCiudad("Pereira"))
                .thenReturn("Ubicación encontrada");

        mockMvc.perform(get("/empleados/ubicacion/Pereira"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ubicación encontrada"));

        verify(apiOpenStreetMap).buscarCiudad("Pereira");
    }

}
