package it.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.authentication.model.entity.TbUserRoleJoin;

@Repository
public interface TbUserRoleJoinRepository extends JpaRepository<TbUserRoleJoin, Integer> {

}
