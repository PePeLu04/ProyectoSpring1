package com.proyecto.proyectoWeb.repository;

import com.proyecto.proyectoWeb.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RepositoryRestaurante extends JpaRepository<Restaurante, Long> {
    @Transactional
    @Modifying
    @Query(value = "update restaurante set comida_id = ?2 where id = ?1", nativeQuery = true)
    void actualizarFKComida(int restaurante_id, int comida_id);

    @Query(value = "SELECT * FROM restaurante", nativeQuery = true)
    public List<Restaurante> findAll();

}
