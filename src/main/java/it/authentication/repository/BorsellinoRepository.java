package it.authentication.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.authentication.model.entity.Borsellino;

public interface BorsellinoRepository extends JpaRepository<Borsellino, Long>{
	List<Borsellino> findByIdMinorenni (Long idMinorenni);
	Borsellino findByBorsellinoUUID (UUID borsellinoUUID);

}
