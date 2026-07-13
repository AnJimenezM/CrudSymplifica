package com.crud.backendcrud.service;
import com.crud.backendcrud.entity.Beneficio;

import java.util.List;


public interface BeneficioService {

    List<Beneficio> listar();
    List<Beneficio> listarPorEmpleado(Integer idEmpleado);
    Beneficio buscarPorId(Integer id);
    Beneficio guardar(Beneficio beneficio);
    Beneficio actualizar(Integer id, Beneficio beneficio);
    void eliminar(Integer id);
}
