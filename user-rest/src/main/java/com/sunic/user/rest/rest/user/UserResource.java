package com.sunic.user.rest.rest.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunic.user.spec.common.rdo.BaseResponse;
import com.sunic.user.rest.rest.user.docs.UserResourceDocs;
import com.sunic.user.spec.facade.user.UserFacade;
import com.sunic.user.spec.facade.user.rdo.UserLoginRdo;
import com.sunic.user.spec.facade.user.sdo.UserJoinSdo;
import com.sunic.user.spec.facade.user.sdo.UserLoginSdo;
import com.sunic.user.spec.facade.user.sdo.UserRegisterSdo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource implements UserResourceDocs {
    
    private final UserFacade userFacade;

    @Override
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerUser(@RequestBody UserRegisterSdo userRegisterSdo) {
        userFacade.registerUser(userRegisterSdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success"), HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<BaseResponse> loginUser(@RequestBody UserLoginSdo userLoginSdo) {
        UserLoginRdo loginResult = userFacade.loginUser(userLoginSdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success", loginResult), HttpStatus.OK);
    }

    @Override
    @PostMapping("/deactivate")
    public ResponseEntity<BaseResponse> deactivateUser() {
        userFacade.deactivateUser();
        return new ResponseEntity<>(BaseResponse.from(true, "Success"), HttpStatus.OK);
    }

    @Override
    @PostMapping("/join")
    public ResponseEntity<BaseResponse> joinWorkspace(@RequestBody UserJoinSdo userJoinSdo) {
        userFacade.joinWorkspace(userJoinSdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success"), HttpStatus.OK);
    }
}