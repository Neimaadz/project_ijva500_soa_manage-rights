package com.cedalanavi.project_ijva500_soa_managerights.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cedalanavi.project_ijva500_soa_managerights.Data.ReferentialUserRightCreateRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.ReferentialUserRightUpdateRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.UserRightsCreateRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.UserRightsUpdateRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Entities.ReferentialUserRight;
import com.cedalanavi.project_ijva500_soa_managerights.Entities.UserRight;
import com.cedalanavi.project_ijva500_soa_managerights.Repositories.ManageRightsRepository;
import com.cedalanavi.project_ijva500_soa_managerights.Repositories.ReferentialUserRightRepository;

@Service
public class ManageRightsService {

	@Autowired
	ManageRightsRepository manageRightsRepository;
	
	@Autowired
	ReferentialUserRightRepository referentialUserRightRepository;

	public List<ReferentialUserRight> getAllUserRightReferentials() {
		return referentialUserRightRepository.findAll();
	}

	public List<ReferentialUserRight> addUserRightReferentials(ReferentialUserRightCreateRequest referentialUserRightCreateRequest) {
		List<ReferentialUserRight> existReferentialUserRights = referentialUserRightRepository.findByLabelIn(referentialUserRightCreateRequest.labels);
		List<ReferentialUserRight> referentialUserRights = new ArrayList<ReferentialUserRight>();

		// Throw exception if one element of referential user right already exist
		if (existReferentialUserRights.size() != 0) {
			throw new IllegalArgumentException("Values already exists.");
		}

		referentialUserRightCreateRequest.labels.forEach(t -> {
			ReferentialUserRight referentialUserRight = new ReferentialUserRight();
			referentialUserRight.setLabel(t);
			
			referentialUserRights.add(referentialUserRight);
		});

		referentialUserRightRepository.saveAll(referentialUserRights);
		
		return referentialUserRightRepository.findAll();
	}

	public List<ReferentialUserRight> updateUserRightReferentials(ReferentialUserRightUpdateRequest referentialUserRightUpdateRequest) {
		Optional<ReferentialUserRight> existReferentialUserRight = referentialUserRightRepository.findById(referentialUserRightUpdateRequest.idRight);

		if (!existReferentialUserRight.isPresent()) {
			throw new NoSuchElementException();
		}
		existReferentialUserRight.get().setLabel(referentialUserRightUpdateRequest.label);
		referentialUserRightRepository.save(existReferentialUserRight.get());
		
		return referentialUserRightRepository.findAll();
	}
	
	public List<UserRight> getUserRightsOfUsers() {
		return manageRightsRepository.findAll();
	}
	
	public UserRight getUserRightsByUsername(String username) {
		return manageRightsRepository.findByUsername(username).get();
	}
	
	public UserRight addUserRights(UserRightsCreateRequest userRightsCreateRequest) {
		Optional<UserRight> existUserRight = manageRightsRepository.findByIdUser(userRightsCreateRequest.idUser);
		List<ReferentialUserRight> referentialUserRights = referentialUserRightRepository.findByLabelIn(userRightsCreateRequest.referentialUserRights);
		UserRight createUserRight = new UserRight();
		
		// Throw exception if one element of referential user right not found in database
		if (referentialUserRights.size() != userRightsCreateRequest.referentialUserRights.size()) {
			throw new NoSuchElementException();
		}
		
		if (existUserRight.isPresent()) {
			List<ReferentialUserRight> existReferentialUserRights = existUserRight.get().getReferentialUserRights();
			
			// Throw exception if user already have all rights
			if (existReferentialUserRights.containsAll(referentialUserRights)) {
				throw new IllegalArgumentException("Values already exists.");
			}
			
			// Keep only not existing user right
			referentialUserRights.removeIf(t -> existReferentialUserRights.contains(t));
			existReferentialUserRights.addAll(referentialUserRights);
			
			createUserRight = existUserRight.get();
		} else {
			createUserRight.setIdUser(userRightsCreateRequest.idUser);
			createUserRight.setReferentialUserRights(referentialUserRights);
		}
		createUserRight.setUsername(userRightsCreateRequest.username);
		manageRightsRepository.save(createUserRight);
		
		return createUserRight;
	}
	
	public UserRight updateUserRights(String idUser, UserRightsUpdateRequest userRightsUpdateRequest) {
		UserRight existUserRight = manageRightsRepository.findByIdUser(idUser).orElseThrow();
		List<ReferentialUserRight> referentialUserRights = referentialUserRightRepository.findByLabelIn(userRightsUpdateRequest.referentialUserRights);

		// Throw exception if one element of referential user right not found in database
		if (referentialUserRights.size() != userRightsUpdateRequest.referentialUserRights.size()) {
			throw new NoSuchElementException();
		}
		existUserRight.setReferentialUserRights(referentialUserRights);
		
		return manageRightsRepository.save(existUserRight);
	}

	@Transactional
	public void deleteUserRights(String idUser) {
		manageRightsRepository.deleteByIdUser(idUser);
	}
}
