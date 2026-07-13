package com.crud.backendcrud.service.impl;
import com.crud.backendcrud.entity.Empleado;
import com.crud.backendcrud.repository.EmpleadoRepository;
import com.crud.backendcrud.service.EmpleadoService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    private final EmpleadoRepository repository;

    public EmpleadoServiceImpl(EmpleadoRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Empleado> listar() {

        return repository.findAll();
    }

    @Override
    public Empleado buscarPorId(Integer id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        System.out.println("servicio iniciado");

        return repository.save(empleado);
    }

    @Override
    public Empleado actualizar(Integer id, Empleado empleado) {
        Empleado existente = repository.findById(id).orElse(null);
        if(existente == null){
            return null;
        }

        existente.setNombre(empleado.getNombre());
        existente.setApellido((empleado.getApellido()));
        existente.setCorreo(empleado.getCorreo());
        existente.setCiudad(empleado.getCiudad());
        existente.setPosicion(empleado.getPosicion());

        return  repository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {

        repository.deleteById(id);

    }
}
