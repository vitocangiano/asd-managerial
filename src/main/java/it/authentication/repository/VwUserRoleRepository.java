package it.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.authentication.model.entity.VwUserRole;

@Repository
public interface VwUserRoleRepository extends JpaRepository<VwUserRole, Integer> {

}
