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

import org.springframework.lang.NonNull;

@Entity
@Table(name = "referentialUserRight")
public class ReferentialUserRight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idRight;
	
	@NonNull
	private String label;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable( name = "userRightAssociation",
                joinColumns = @JoinColumn(name = "idRight"),
                inverseJoinColumns = @JoinColumn( name = "idUserRight") )
    private List<UserRight> userRights;

	public int getIdRight() {
		return idRight;
	}

	public void setIdRight(int idRight) {
		this.idRight = idRight;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
