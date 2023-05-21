package com.proyecto.proyectoWeb.services;

import com.proyecto.proyectoWeb.models.Restaurante;
import com.proyecto.proyectoWeb.models.Comida;
import com.proyecto.proyectoWeb.repository.RepositoryRestaurante;
import com.proyecto.proyectoWeb.repository.RepositoryComida;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesComida implements InterfacesComida {


    RepositoryComida repositoryComida;
    RepositoryRestaurante repositoryRestaurante;
    public ServicesComida(RepositoryComida repositoryComida, RepositoryRestaurante repositoryRestaurante) {
        this.repositoryComida = repositoryComida;
        this.repositoryRestaurante = repositoryRestaurante;
    }


    @Override
    public List<Comida> findAll(){
        return repositoryComida.findAll();
    }

    @Override
    public Optional<Comida> findComidaByEvery(String name, String origen){
        return repositoryComida.findComidaByEvery(name, origen);
    }


    public void save(Comida comida){
        // Update FK Restaurante
        List<Restaurante> Restaurante = comida.getRestaurante();
        repositoryComida.save(comida);
        Optional<Comida> aux = repositoryComida.findComidaByEvery(comida.getNombre(), comida.getOrigen());
        Comida ComidaActual = aux.orElseThrow(() ->
                new RuntimeException("El plato no existe")
        );
        for (Restaurante restaurante : Restaurante) {
            repositoryRestaurante.actualizarFKComida(restaurante.getId(), ComidaActual.getId());
        }
    }

}
