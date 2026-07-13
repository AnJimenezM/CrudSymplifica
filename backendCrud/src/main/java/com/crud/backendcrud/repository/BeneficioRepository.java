package com.crud.backendcrud.repository;

import com.crud.backendcrud.entity.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BeneficioRepository extends JpaRepository<Beneficio, Integer> {

    @Query("SELECT b FROM Beneficio b WHERE b.empleado.id_empleado = :idEmpleado")
    List<Beneficio> listarPorEmpleado(@Param("idEmpleado") Integer idEmpleado);

}
