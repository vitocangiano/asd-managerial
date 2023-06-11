package it.authentication.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.authentication.model.entity.Pagamenti;
@Repository
public interface Pagamentirepository extends JpaRepository<Pagamenti, Long> {
	List<Pagamenti> findByIdMaggiorenne (Long idMaggiorenni);
	
	Pagamenti findByPagamentiUUID(UUID pagamentoUUID);
	
	 @Query(value = "SELECT MAX(p.numero_pagamento) FROM pagamenti p", nativeQuery = true)
	    Integer ultimaRicevuta();

}
