package com.crud.backendcrud.service.impl;
import com.crud.backendcrud.entity.Beneficio;
import com.crud.backendcrud.repository.BeneficioRepository;
import com.crud.backendcrud.service.BeneficioService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class BeneficioServiceImpl implements BeneficioService{

    private final BeneficioRepository repository;

    public BeneficioServiceImpl(BeneficioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Beneficio> listar() {
        return repository.findAll();
    }

    @Override
    public Beneficio buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Beneficio guardar(Beneficio beneficio) {
        return repository.save(beneficio);
    }



    @Override
    public Beneficio actualizar(Integer id, Beneficio beneficio) {
        Beneficio existente = repository.findById(id).orElse(null);
        if(existente == null){
            return null;
        }

        existente.setNombre_beneficio(beneficio.getNombre_beneficio());
        existente.setMonto(beneficio.getMonto());
        existente.setEmpleado(beneficio.getEmpleado());


        return  repository.save(existente);

    }

    @Override
    public void eliminar(Integer id) {

        repository.deleteById(id);

    }

    @Override
    public List<Beneficio> listarPorEmpleado(Integer idEmpleado) {
        return repository.listarPorEmpleado(idEmpleado);
    }
}
