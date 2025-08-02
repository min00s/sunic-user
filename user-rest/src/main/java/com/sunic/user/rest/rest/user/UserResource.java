package com.sunic.user.rest.rest.user;

import com.sunic.user.aggregate.user.logic.UserLogic;
import com.sunic.user.spec.common.CommonResponse;
import com.sunic.user.spec.user.facade.UserFacade;
import com.sunic.user.spec.user.facade.sdo.UserJoinSdo;
import com.sunic.user.spec.user.facade.sdo.UserLoginRdo;
import com.sunic.user.spec.user.facade.sdo.UserLoginSdo;
import com.sunic.user.spec.user.facade.sdo.UserRegisterSdo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource implements UserFacade {

    private final UserLogic userLogic;

    @Override
    @PostMapping("/register")
    public ResponseEntity<CommonResponse> registerUser(@RequestBody UserRegisterSdo userRegisterSdo) {
        userLogic.registerUser(userRegisterSdo);
        return new ResponseEntity<>(CommonResponse.from(true, "Success"), HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<CommonResponse> loginUser(@RequestBody UserLoginSdo userLoginSdo) {
        UserLoginRdo loginResult = userLogic.loginUser(userLoginSdo);
        return new ResponseEntity<>(CommonResponse.from(true, "Success", loginResult), HttpStatus.OK);
    }

    @Override
    @PostMapping("/deactivate")
    public ResponseEntity<CommonResponse> deactivateUser() {
        userLogic.deactivateUser();
        return new ResponseEntity<>(CommonResponse.from(true, "Success"), HttpStatus.OK);
    }

    @Override
    @PostMapping("/join")
    public ResponseEntity<CommonResponse> joinWorkspace(@RequestBody UserJoinSdo userJoinSdo) {
        userLogic.joinWorkspace(userJoinSdo);
        return new ResponseEntity<>(CommonResponse.from(true, "Success"), HttpStatus.OK);
    }
}