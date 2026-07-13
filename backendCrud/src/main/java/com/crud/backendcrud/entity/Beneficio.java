package com.crud.backendcrud.entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Setter
@Table (name = "beneficio")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Beneficio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_beneficio")
    private Integer id_beneficio;

    @Column(name = "nombre_beneficio")
    private String nombre_beneficio;

    @Column(name = "monto")
    private Integer monto;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    @JsonBackReference
    private Empleado empleado;
}
