package com.proyecto.proyectoWeb.services;

import com.proyecto.proyectoWeb.models.Restaurante;

import java.util.List;

public interface InterfacesRestaurante {

    public List<Restaurante> findAll();

    void actualizarFKComida(int restaurante_id, int comida_id);


}
