package com.crud.backendcrud.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "empleado")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Empleado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer id_empleado;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "posicion")
    private String posicion;

    @OneToMany(mappedBy = "empleado")
    @JsonManagedReference
    private List<Beneficio> beneficios;

}