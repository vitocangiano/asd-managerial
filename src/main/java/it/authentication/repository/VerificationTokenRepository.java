package it.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.authentication.model.entity.VerificationToken;



public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
		VerificationToken findByToken (String token);
		VerificationToken findByMaggiorenneId(long maggiorenneId);
}
