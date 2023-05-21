package com.proyecto.proyectoWeb.services;

import com.proyecto.proyectoWeb.models.Propietario;
import com.proyecto.proyectoWeb.repository.RepositoryPropietario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesPropietario implements InterfacesPropietario {

    private RepositoryPropietario propietarioRepository;

    public ServicesPropietario(RepositoryPropietario propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    @Override
    public List<Propietario> getAllPropietarios() {
        return propietarioRepository.findAll();
    }

    @Override
    public Optional<Propietario> findById(int id){ return propietarioRepository.findById(id); }

    @Override
    public void addPropietario(String first_name, String last_name, String email) { propietarioRepository.addPropietario(first_name, last_name, email); }

    @Override
    public void deletePropietario(int id){ propietarioRepository.deletePropietario(id); }

    @Override
    public void editPropietario(int id, String first_name, String last_name, String email){ propietarioRepository.editPropietario(id, first_name, last_name, email); }

    public void save(Propietario propietario){ propietarioRepository.save(propietario);}

}