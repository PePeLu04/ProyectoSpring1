package com.proyecto.proyectoWeb.repository;

import com.proyecto.proyectoWeb.models.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RepositoryComida extends JpaRepository<Comida, Long> {

    @Query(value = "SELECT * FROM comida", nativeQuery = true)
    public List<Comida> findAll();

    @Query(value = "SELECT * FROM comida WHERE nombre = ?1 AND origen = ?2", nativeQuery = true )
    Optional<Comida> findComidaByEvery(String name, String origen);
    @Modifying
    @Transactional
    @Query(
            value =
                    "INSERT INTO comida (nombre, origen) values (:nombre, :origen)",
            nativeQuery = true)
    void addComida(@Param("nombre") String nombre, @Param("origen") String origen);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comida WHERE ID=:id", nativeQuery = true)
    void deleteComida(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE comida SET nombre=:nombre, origen=:origen WHERE ID=:id", nativeQuery = true)
    void editComida(@Param("id") int id, @Param("nombre") String name, @Param("origen") String origen);
}
