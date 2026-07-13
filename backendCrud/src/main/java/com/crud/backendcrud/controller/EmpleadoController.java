package com.crud.backendcrud.controller;
import com.crud.backendcrud.entity.Empleado;
import com.crud.backendcrud.service.ApiOpenStreetMap;
import com.crud.backendcrud.service.EmpleadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/employees")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final ApiOpenStreetMap apiOpenStreetMap;

    public  EmpleadoController(EmpleadoService empleadoService, ApiOpenStreetMap apiOpenStreetMap){
        this.empleadoService = empleadoService;
        this.apiOpenStreetMap = apiOpenStreetMap;

    }

    @GetMapping
    public List<Empleado>listar(){
        return  empleadoService.listar();
    }
    @GetMapping("/{id}")
    public Empleado buscarporId(@PathVariable Integer id){
        System.out.println("controlador");
        return empleadoService.buscarPorId(id);
    }

    @PostMapping
    public Empleado guardar(@RequestBody Empleado empleado){
        return empleadoService.guardar(empleado);
    }

    @PutMapping("/{id}")

    public Empleado actualizar(@PathVariable Integer id, @RequestBody Empleado empleado){
        return empleadoService.actualizar(id, empleado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        empleadoService.eliminar(id);
    }

    @GetMapping("/ubicacion/{ciudad}")
    public String obtenerUbicacion(@PathVariable String ciudad){
        return apiOpenStreetMap.buscarCiudad(ciudad);}

}
