package com.cedalanavi.project_ijva500_soa_managerights.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cedalanavi.project_ijva500_soa_managerights.Data.AddReferentialUserRightRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.UpdateReferentialUserRightRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.UserRightsRequest;
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

	public List<ReferentialUserRight> addUserRightReferentials(AddReferentialUserRightRequest addReferentialUserRightRequest) {
		List<ReferentialUserRight> existReferentialUserRights = referentialUserRightRepository.findByLabelIn(addReferentialUserRightRequest.labels);
		List<ReferentialUserRight> referentialUserRights = new ArrayList<ReferentialUserRight>();

		// Throw exception if one element of referential user right already exist
		if (existReferentialUserRights.size() != 0) {
			throw new IllegalArgumentException("Values already exists.");
		}

		addReferentialUserRightRequest.labels.forEach(t -> {
			ReferentialUserRight referentialUserRight = new ReferentialUserRight();
			referentialUserRight.setLabel(t);
			
			referentialUserRights.add(referentialUserRight);
		});

		referentialUserRightRepository.saveAll(referentialUserRights);
		
		return referentialUserRightRepository.findAll();
	}

	public List<ReferentialUserRight> updateUserRightReferentials(UpdateReferentialUserRightRequest updateReferentialUserRightRequest) {
		Optional<ReferentialUserRight> existReferentialUserRight = referentialUserRightRepository.findById(updateReferentialUserRightRequest.idRight);

		if (!existReferentialUserRight.isPresent()) {
			throw new NoSuchElementException();
		}
		existReferentialUserRight.get().setLabel(updateReferentialUserRightRequest.label);
		referentialUserRightRepository.save(existReferentialUserRight.get());
		
		return referentialUserRightRepository.findAll();
	}
	
	public List<UserRight> getUserRightsOfUsers() {
		return manageRightsRepository.findAll();
	}
	
	public UserRight getUserRightsByUsername(String username) {
		return manageRightsRepository.findByUsername(username).get();
	}
	
	public UserRight addUserRights(UserRightsRequest userRightsRequest) {
		Optional<UserRight> existUserRight = manageRightsRepository.findById(userRightsRequest.idUser);
		List<ReferentialUserRight> referentialUserRights = referentialUserRightRepository.findByLabelIn(userRightsRequest.referentialUserRights);
		UserRight createUserRight = new UserRight();
		
		// Throw exception if one element of referential user right not found in database
		if (referentialUserRights.size() != userRightsRequest.referentialUserRights.size()) {
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
			createUserRight.setIdUser(userRightsRequest.idUser);
			createUserRight.setReferentialUserRights(referentialUserRights);
		}
		createUserRight.setUsername(userRightsRequest.username);
		manageRightsRepository.save(createUserRight);
		
		return createUserRight;
	}
	
	public UserRight updateUserRights(UserRightsRequest userRightsRequest) {
		Optional<UserRight> existUserRight = manageRightsRepository.findById(userRightsRequest.idUser);
		List<ReferentialUserRight> referentialUserRights = referentialUserRightRepository.findByLabelIn(userRightsRequest.referentialUserRights);
		UserRight createUserRight = new UserRight();

		// Throw exception if one element of referential user right not found in database
		if (referentialUserRights.size() != userRightsRequest.referentialUserRights.size()) {
			throw new NoSuchElementException();
		}
		
		if (existUserRight.isPresent()) {
			existUserRight.get().setReferentialUserRights(referentialUserRights);;
			createUserRight = existUserRight.get();
			createUserRight.setUsername(userRightsRequest.username);
			
			manageRightsRepository.save(createUserRight);
		}
		
		return createUserRight;
	}
}
