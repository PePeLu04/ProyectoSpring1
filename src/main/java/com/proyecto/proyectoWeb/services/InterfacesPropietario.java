package com.proyecto.proyectoWeb.services;

import com.proyecto.proyectoWeb.models.Propietario;

import java.util.List;
import java.util.Optional;

public interface InterfacesPropietario {

    List<Propietario> getAllPropietarios();
    public Optional<Propietario> findById(int id);

    void addPropietario(String first_name, String last_name, String email);
    void deletePropietario(int id);
    void editPropietario(int id, String first_name, String last_name, String email);


}