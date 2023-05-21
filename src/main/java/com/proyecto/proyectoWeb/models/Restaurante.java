package com.proyecto.proyectoWeb.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="restaurante")
public class Restaurante implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;

    @Column(name = "nombre")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String nombre;

    @Column(name = "ubicacion")
    @JdbcTypeCode(SqlTypes.CHAR)
    private String ubicacion;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "restaurante_horarios",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "horario_id"))
    private List<Horario> horarios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "comida_id")
    private Comida comida;



}
