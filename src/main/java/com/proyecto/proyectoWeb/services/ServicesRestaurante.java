package com.proyecto.proyectoWeb.services;

import com.proyecto.proyectoWeb.models.Restaurante;
import com.proyecto.proyectoWeb.repository.RepositoryRestaurante;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesRestaurante implements InterfacesRestaurante {


    RepositoryRestaurante repositoryRestaurante;

    public ServicesRestaurante(RepositoryRestaurante repositoryRestaurante) {
        this.repositoryRestaurante = repositoryRestaurante;
    }

    @Override
    public List<Restaurante> findAll(){
        return repositoryRestaurante.findAll();
    };

    public void save(Restaurante restaurante){
        repositoryRestaurante.save(restaurante);
    }

    @Override
    public void actualizarFKComida(int restaurante_id, int comida_id){ repositoryRestaurante.actualizarFKComida(restaurante_id, comida_id);}


}
