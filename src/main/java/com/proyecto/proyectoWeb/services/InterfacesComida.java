package com.proyecto.proyectoWeb.services;

import com.proyecto.proyectoWeb.models.Comida;

import java.util.List;
import java.util.Optional;

public interface InterfacesComida {

    public List<Comida> findAll();

    Optional<Comida> findComidaByEvery(String name, String origen);


}
