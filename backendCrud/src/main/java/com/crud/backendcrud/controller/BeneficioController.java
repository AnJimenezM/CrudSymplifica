package com.crud.backendcrud.controller;
import com.crud.backendcrud.entity.Beneficio;
import com.crud.backendcrud.service.BeneficioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
//paquete para gestionar la creacion del empleado
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PutMapping;
//paquete para gestionar la eliminacion del empleado
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/beneficios")
public class BeneficioController {

    private final BeneficioService beneficioService;

    public BeneficioController(BeneficioService beneficioService){
        this.beneficioService = beneficioService;
    }

    @GetMapping
    public List<Beneficio>listar(){return beneficioService.listar();}

    @GetMapping("/{id}")
    public Beneficio buscarPorId(@PathVariable Integer id){
        return  beneficioService.buscarPorId(id);
    }

    @GetMapping("/employees/{id}")
    public List<Beneficio> listarPorEmpleado(@PathVariable Integer id) {
        return beneficioService.listarPorEmpleado(id);
    }

    @PostMapping
    public Beneficio guardar(@RequestBody Beneficio beneficio){
        return  beneficioService.guardar(beneficio);
    }

    @PutMapping("/{id}")
    public Beneficio actualizar(@PathVariable Integer id, @RequestBody Beneficio beneficio){
        return beneficioService.actualizar(id, beneficio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        beneficioService.eliminar(id);
    }

}
