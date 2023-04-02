package com.cedalanavi.project_ijva500_soa_managerights.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "userRight")
public class UserRight {
	
	@Id
	private int idUser;

	private String username;
	
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable( name = "userRightAssociation",
                joinColumns = @JoinColumn(name = "idUser"),
                inverseJoinColumns = @JoinColumn( name = "idRight" ) )
	private List<ReferentialUserRight> referentialUserRights;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ReferentialUserRight> getReferentialUserRights() {
		return referentialUserRights;
	}

	public void setReferentialUserRights(List<ReferentialUserRight> referentialUserRights) {
		this.referentialUserRights = referentialUserRights;
	}
    
}
