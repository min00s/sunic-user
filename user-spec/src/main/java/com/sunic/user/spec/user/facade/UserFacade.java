package com.sunic.user.spec.user.facade;

import com.sunic.user.spec.common.CommonResponse;
import com.sunic.user.spec.user.facade.sdo.UserJoinSdo;
import com.sunic.user.spec.user.facade.sdo.UserLoginSdo;
import com.sunic.user.spec.user.facade.sdo.UserRegisterSdo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "User API", description = "register, login, join workspace etc")
public interface UserFacade {

    @Operation(summary = "register", description = "register API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<CommonResponse> registerUser(UserRegisterSdo userRegisterSdo);

    @Operation(summary = "login", description = "login API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<CommonResponse> loginUser(UserLoginSdo userLoginSdo);

    @Operation(summary = "deactivate", description = "deactivate API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<CommonResponse> deactivateUser();

    @Operation(summary = "join workspace", description = "join workspace API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<CommonResponse> joinWorkspace(UserJoinSdo userJoinSdo);
}