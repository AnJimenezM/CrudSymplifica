package com.crud.backendcrud.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PeticionApi {

    private Integer id_empleado;

    private String nombre;

    private String apellido;

    private String correo;

    private String ciudad;

    private String posicion;

    private String latitud;

    private String longitud;
}
