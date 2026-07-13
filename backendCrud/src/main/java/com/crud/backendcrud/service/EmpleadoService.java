package com.crud.backendcrud.service;
import com.crud.backendcrud.entity.Empleado;

import java.util.List;


public interface EmpleadoService {
    List<Empleado> listar();
    Empleado buscarPorId(Integer id);
    Empleado guardar(Empleado empleado);
    Empleado actualizar(Integer id, Empleado empleado);
    void eliminar(Integer id);
}
