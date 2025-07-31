package com.sunic.user.rest.rest.user.docs;

import org.springframework.http.ResponseEntity;

import com.sunic.user.rest.config.dto.BaseResponse;
import com.sunic.user.spec.facade.user.sdo.UserJoinSdo;
import com.sunic.user.spec.facade.user.sdo.UserLoginSdo;
import com.sunic.user.spec.facade.user.sdo.UserRegisterSdo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User API", description = "register, login, join workspace etc")
public interface UserResourceDocs {

    @Operation(summary = "register", description = "register API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<BaseResponse> registerUser(UserRegisterSdo userRegisterSdo);

    @Operation(summary = "login", description = "login API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<BaseResponse> loginUser(UserLoginSdo userLoginSdo);

    @Operation(summary = "deactivate", description = "deactivate API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<BaseResponse> deactivateUser();

    @Operation(summary = "join workspace", description = "join workspace API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<BaseResponse> joinWorkspace(UserJoinSdo userJoinSdo);
}