package it.authentication.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.authentication.model.entity.Maggiorenni;



public interface MaggiorenniRepository extends JpaRepository<Maggiorenni, Long>{
	Maggiorenni findByMaggiorenniUUID(UUID maggiorenniUUID);
	Maggiorenni findByEmail(String email);
}
