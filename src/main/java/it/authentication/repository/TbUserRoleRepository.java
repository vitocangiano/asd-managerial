package it.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.authentication.model.entity.TbUserRole;

@Repository
public interface TbUserRoleRepository extends JpaRepository<TbUserRole, Integer> {
	public TbUserRole findByName(String name);

}
