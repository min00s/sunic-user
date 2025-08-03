package com.sunic.user.rest.rest.userworkspace;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunic.user.aggregate.userworkspace.logic.UserWorkspaceLogic;
import com.sunic.user.spec.common.CommonResponse;
import com.sunic.user.spec.userworkspace.facade.UserWorkspaceFacade;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceCdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceRdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceUdo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workspace")
@RequiredArgsConstructor
public class UserWorkspaceResource implements UserWorkspaceFacade {

	private final UserWorkspaceLogic userWorkspaceLogic;

	@Override
	@PostMapping("/")
	public ResponseEntity<CommonResponse> registerUserWorkspace(
		@RequestBody UserWorkspaceCdo userWorkspaceRegisterSdo) {
		userWorkspaceLogic.registerUserWorkspace(userWorkspaceRegisterSdo);
		return new ResponseEntity<>(CommonResponse.from(true, "Success"), HttpStatus.OK);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<CommonResponse> retrieveUserWorkspace(@PathVariable Integer id) {
		UserWorkspaceRdo workspace = userWorkspaceLogic.retrieveUserWorkspace(id);
		return new ResponseEntity<>(CommonResponse.from(true, "Success", workspace), HttpStatus.OK);
	}

	@Override
	@PutMapping("/")
	public ResponseEntity<CommonResponse> modifyUserWorkspace(@RequestBody UserWorkspaceUdo userWorkspaceModifySdo) {
		String result = userWorkspaceLogic.modifyUserWorkspace(userWorkspaceModifySdo);
		return new ResponseEntity<>(CommonResponse.from(true, "Success", result), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponse> deleteUserWorkspace(@PathVariable Integer id) {
		userWorkspaceLogic.deleteUserWorkspace(id);
		return new ResponseEntity<>(CommonResponse.from(true, "Success"), HttpStatus.OK);
	}
}