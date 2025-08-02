package com.sunic.user.rest.rest.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunic.user.aggregate.user.logic.UserLogic;
import com.sunic.user.spec.common.CommonResponse;
import com.sunic.user.spec.user.facade.UserClientFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/client")
@RequiredArgsConstructor
public class UserClientResource implements UserClientFacade {

	private final UserLogic userLogic;

	@Override
	@GetMapping("/check/{userId}")
	public ResponseEntity<CommonResponse> checkUser(@PathVariable Integer userId) {
		return new ResponseEntity<>(CommonResponse.from(true, "Success", userLogic.checkUser(userId)), HttpStatus.OK);
	}

	@Override
	@GetMapping("/checkAdmin/{userId}")
	public ResponseEntity<CommonResponse> checkUserIsAdmin(@PathVariable Integer userId) {
		return new ResponseEntity<>(CommonResponse.from(true, "Success", userLogic.checkAdminUser(userId)), HttpStatus.OK);
	}
}
