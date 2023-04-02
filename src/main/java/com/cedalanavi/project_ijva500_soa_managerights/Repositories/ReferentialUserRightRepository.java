package com.cedalanavi.project_ijva500_soa_managerights.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cedalanavi.project_ijva500_soa_managerights.Entities.ReferentialUserRight;

public interface ReferentialUserRightRepository extends JpaRepository<ReferentialUserRight, Integer> {

	Optional<ReferentialUserRight> findByLabel(String label);
	
	List<ReferentialUserRight> findByLabelIn(List<String> label);
}
