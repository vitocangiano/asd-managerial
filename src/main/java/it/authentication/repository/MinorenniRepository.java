package it.authentication.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import it.authentication.model.entity.Minorenni;

public interface MinorenniRepository extends JpaRepository<Minorenni, Long>{
	Minorenni findByMinorenneUUID(UUID minorenneUUID);
	List<Minorenni> findByCognome(String cognome);

}
