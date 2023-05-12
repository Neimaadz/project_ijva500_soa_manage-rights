package com.cedalanavi.project_ijva500_soa_managerights.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cedalanavi.project_ijva500_soa_managerights.Entities.UserRight;

public interface ManageRightsRepository extends JpaRepository<UserRight, Integer> {

	Optional<UserRight> findByUsername(String username);
	
	Optional<UserRight> findByIdUser(String idUser);
}
