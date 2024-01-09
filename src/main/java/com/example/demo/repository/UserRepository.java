package com.example.demo.repository;

import com.example.demo.auth.LoginResponse;
import com.example.demo.entity.User;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    @Query(
            value = "SELECT * FROM Users u WHERE u.useremailucab = :email",
            nativeQuery = true
    )
    Optional<User> findByEmailUcab(@Param("email") String email);

    @Query(
            value = "SELECT obtenerRolesUsuario(:userDNIParam)",
            nativeQuery = true
    )
    Iterable<String> getUserRoles(@Param("userDNIParam") String userDNIParam);

    @Query(
            value = "SELECT validarUsuarioYClave(:userDNI,:password)",
            nativeQuery = true
    )
    Boolean validateUserAndPassword(@Param("userDNI") String userDNI, @Param("password") String password);



}
