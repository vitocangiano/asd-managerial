package it.authentication.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the tb_user_role database table.
 * 
 */
@Entity
@Table(name = "tb_user_role")
@NamedQuery(name = "TbUserRole.findAll", query = "SELECT t FROM TbUserRole t")
public class TbUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte userRoleId;

	private byte active;

	private String name;

	private UUID userRoleUUID;

	@JsonIgnore
	@OneToMany(mappedBy = "tbUserRole")
	private List<TbUserRoleJoin> tbUserRoleJoins;

	public TbUserRole() {
		userRoleUUID = UUID.randomUUID();
	}

	public byte getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(byte userRoleId) {
		this.userRoleId = userRoleId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getUserRoleUUID() {
		return userRoleUUID;
	}

	public void setUserRoleUUID(UUID userRoleUUID) {
		this.userRoleUUID = userRoleUUID;
	}

	public List<TbUserRoleJoin> getTbUserRoleJoins() {
		return this.tbUserRoleJoins;
	}

	public void setTbUserRoleJoins(List<TbUserRoleJoin> tbUserRoleJoins) {
		this.tbUserRoleJoins = tbUserRoleJoins;
	}

	public TbUserRoleJoin addTbUserRoleJoin(TbUserRoleJoin tbUserRoleJoin) {
		getTbUserRoleJoins().add(tbUserRoleJoin);
		tbUserRoleJoin.setTbUserRole(this);

		return tbUserRoleJoin;
	}

	public TbUserRoleJoin removeTbUserRoleJoin(TbUserRoleJoin tbUserRoleJoin) {
		getTbUserRoleJoins().remove(tbUserRoleJoin);
		tbUserRoleJoin.setTbUserRole(null);

		return tbUserRoleJoin;
	}

}