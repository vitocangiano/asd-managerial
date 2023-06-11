package it.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.authentication.model.entity.TbUserSession;

@Repository
public interface TbUserSessionRepository extends JpaRepository<TbUserSession, Integer> {

}
