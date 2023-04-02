package com.cedalanavi.project_ijva500_soa_managerights.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedalanavi.project_ijva500_soa_managerights.Data.AddReferentialUserRightRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.UpdateReferentialUserRightRequest;
import com.cedalanavi.project_ijva500_soa_managerights.Data.UserRightsRequest;
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
	public List<ReferentialUserRight> addUserRightReferentials(@RequestBody AddReferentialUserRightRequest addReferentialUserRightRequest) {
		return manageRightsService.addUserRightReferentials(addReferentialUserRightRequest);
	}
	
	@PutMapping("/user-referentials/update")
	public List<ReferentialUserRight> updateUserRightReferentials(@RequestBody UpdateReferentialUserRightRequest updateReferentialUserRightRequest) {
		return manageRightsService.updateUserRightReferentials(updateReferentialUserRightRequest);
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
	public UserRight addUserRights(@RequestBody UserRightsRequest userRightsRequest) {
		return manageRightsService.addUserRights(userRightsRequest);
	}
	
	@PutMapping("/user/update")
	public UserRight updateUserRights(@RequestBody UserRightsRequest userRightsRequest) {
		return manageRightsService.updateUserRights(userRightsRequest);
	}
}
