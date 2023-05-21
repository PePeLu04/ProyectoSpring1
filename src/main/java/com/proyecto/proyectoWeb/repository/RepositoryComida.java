package com.proyecto.proyectoWeb.repository;

import com.proyecto.proyectoWeb.models.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryComida extends JpaRepository<Comida, Long> {

    @Query(value = "SELECT * FROM comida", nativeQuery = true)
    public List<Comida> findAll();

    @Query(value = "SELECT * FROM comida WHERE nombre = ?1 AND origen = ?2", nativeQuery = true )
    Optional<Comida> findComidaByEvery(String name, String origen);
}
