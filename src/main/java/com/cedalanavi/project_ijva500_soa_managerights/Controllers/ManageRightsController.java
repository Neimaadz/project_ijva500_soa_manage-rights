package com.cedalanavi.project_ijva500_soa_managerights.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedalanavi.project_ijva500_soa_managerights.Data.ReferentialUserRightCreateRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.ReferentialUserRightUpdateRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.UserRightsCreateRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.UserRightsUpdateRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Entities.ReferentialUserRight;
import com.cedalanavi.project_ijva500_soa_managerights.Entities.UserRight;
import com.cedalanavi.project_ijva500_soa_managerights.Services.ManageRightsService;

@RestController
@RequestMapping("manage-rights")
public class ManageRightsController {
	
	@Autowired
	ManageRightsService manageRightsService;

	@GetMapping("/user-referentials")
	public List<ReferentialUserRight> getAllUserRightReferentials() {
		return manageRightsService.getAllUserRightReferentials();
	}

	@PostMapping("/user-referentials/add")
	public List<ReferentialUserRight> addUserRightReferentials(@RequestBody ReferentialUserRightCreateRequest referentialUserRightCreateRequest) {
		return manageRightsService.addUserRightReferentials(referentialUserRightCreateRequest);
	}
	
	@PutMapping("/user-referentials/update")
	public List<ReferentialUserRight> updateUserRightReferentials(@RequestBody ReferentialUserRightUpdateRequest referentialUserRightUpdateRequest) {
		return manageRightsService.updateUserRightReferentials(referentialUserRightUpdateRequest);
	}

	@GetMapping("/users")
	public List<UserRight> getUserRightsOfUsers() {
		return manageRightsService.getUserRightsOfUsers();
	}
	
	@GetMapping("/user/{username}")
	public UserRight getUserRights(@PathVariable String username) {
		return manageRightsService.getUserRightsByUsername(username);
	}

	@PostMapping("/user/add")
	public UserRight addUserRights(@RequestBody UserRightsCreateRequest userRightsCreateRequest) {
		return manageRightsService.addUserRights(userRightsCreateRequest);
	}
	
	@PutMapping("/user/update/{idUser}")
	public UserRight updateUserRights(@PathVariable String idUser, @RequestBody UserRightsUpdateRequest userRightsUpdateRequest) {
		return manageRightsService.updateUserRights(idUser, userRightsUpdateRequest);
	}
	
	@DeleteMapping("/delete/{idUser}")
	public void deleteUserRights(@PathVariable String idUser) {
		manageRightsService.deleteUserRights(idUser);
	}
}
