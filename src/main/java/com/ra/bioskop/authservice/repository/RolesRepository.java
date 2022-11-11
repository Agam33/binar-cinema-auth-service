package com.ra.bioskop.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ra.bioskop.authservice.model.ERoles;
import com.ra.bioskop.authservice.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(ERoles name);
}
