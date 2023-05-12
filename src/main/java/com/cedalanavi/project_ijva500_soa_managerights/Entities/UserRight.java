package com.cedalanavi.project_ijva500_soa_managerights.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "userRight")
public class UserRight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUserRight;
	
	private String idUser;

	private String username;
	
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable( name = "userRightAssociation",
                joinColumns = @JoinColumn(name = "idUserRight"),
                inverseJoinColumns = @JoinColumn( name = "idRight" ) )
	private List<ReferentialUserRight> referentialUserRights;

	public int getIdUserRight() {
		return idUserRight;
	}

	public void setIdUserRight(int idUserRight) {
		this.idUserRight = idUserRight;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
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
