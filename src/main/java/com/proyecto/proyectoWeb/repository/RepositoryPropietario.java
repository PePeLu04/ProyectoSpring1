package com.proyecto.proyectoWeb.repository;

import com.proyecto.proyectoWeb.models.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface RepositoryPropietario extends JpaRepository<Propietario, Long> {


    @Query(value = "SELECT * FROM Propietarios", nativeQuery = true)
    public List<Propietario> findAll();

    @Query(value = "SELECT * FROM Propietarios WHERE ID = ?1", nativeQuery = true)
    public Optional<Propietario> findById(int id);

    @Modifying
    @Transactional
    @Query(
            value =
                    "INSERT INTO Propietario (first_name, last_name, email) values (:FirstName, :LastName, :email)",
            nativeQuery = true)
    void addPropietario(@Param("FirstName") String first_name, @Param("LastName") String last_name,
                    @Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Propietario WHERE ID=:id", nativeQuery = true)
    void deletePropietario(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Propietario SET first_name=:FirstName, last_name=:LastName, email=:email WHERE ID=:id", nativeQuery = true)
    void editPropietario(@Param("id") int id, @Param("FirstName") String first_name, @Param("LastName") String last_name,
                     @Param("email") String email);


}
