package it.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.authentication.model.entity.VwUserSession;

@Repository
public interface VwUserSessionRepository extends JpaRepository<VwUserSession, Integer> {

}
