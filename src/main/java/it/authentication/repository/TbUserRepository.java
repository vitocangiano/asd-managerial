package it.authentication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.authentication.model.entity.TbUser;

@Repository
public interface TbUserRepository extends JpaRepository<TbUser, Integer> {
	Optional<TbUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
