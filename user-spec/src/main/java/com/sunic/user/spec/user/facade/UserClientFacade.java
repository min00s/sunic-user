package com.sunic.user.spec.user.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.sunic.user.spec.common.CommonResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Client API", description = "API For Micro Service")
public interface UserClientFacade {

    @Operation(summary = "check User", description = "API For Checking userId is Valid")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<CommonResponse> checkUser(@PathVariable Integer userId);

    @Operation(summary = "check User Admin", description = "API For Checking userId is Valid")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
    ResponseEntity<CommonResponse> checkUserIsAdmin(@PathVariable Integer userId);
}