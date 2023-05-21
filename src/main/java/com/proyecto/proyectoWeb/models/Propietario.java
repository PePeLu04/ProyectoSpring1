package com.proyecto.proyectoWeb.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Propietarios")
public class Propietario implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;

    @Column(name="first_name", nullable=false)
    public String firstName;

    @Column(name="last_name")
    public String lastName;

    @Column(name="email")
    public String email;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "propietarios_restaurantes",
            joinColumns = @JoinColumn(name = "propietario_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurante_id"))
    private List<Restaurante> restaurante = new ArrayList<>();




}