package com.sunic.user.rest.rest.user;

import com.sunic.user.aggregate.user.logic.UserLogic;
import com.sunic.user.spec.common.vo.BaseResponse;
import com.sunic.user.spec.facade.user.UserFacade;
import com.sunic.user.spec.facade.user.vo.UserJoinSdo;
import com.sunic.user.spec.facade.user.vo.UserLoginRdo;
import com.sunic.user.spec.facade.user.vo.UserLoginSdo;
import com.sunic.user.spec.facade.user.vo.UserRegisterSdo;
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
    public ResponseEntity<BaseResponse> registerUser(@RequestBody UserRegisterSdo userRegisterSdo) {
        userLogic.registerUser(userRegisterSdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success"), HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<BaseResponse> loginUser(@RequestBody UserLoginSdo userLoginSdo) {
        UserLoginRdo loginResult = userLogic.loginUser(userLoginSdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success", loginResult), HttpStatus.OK);
    }

    @Override
    @PostMapping("/deactivate")
    public ResponseEntity<BaseResponse> deactivateUser() {
        userLogic.deactivateUser();
        return new ResponseEntity<>(BaseResponse.from(true, "Success"), HttpStatus.OK);
    }

    @Override
    @PostMapping("/join")
    public ResponseEntity<BaseResponse> joinWorkspace(@RequestBody UserJoinSdo userJoinSdo) {
        userLogic.joinWorkspace(userJoinSdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success"), HttpStatus.OK);
    }
}