package com.proyecto.proyectoWeb.services;

import com.proyecto.proyectoWeb.models.Comida;

import java.util.List;
import java.util.Optional;

public interface InterfacesComida {

    public List<Comida> findAll();
    void deleteComida(int id);

    Optional<Comida> findComidaByEvery(String name, String origen);
    void editComida(int id, String nombre, String origen);


}
