package com.sunic.user.rest.rest.userworkspace.docs;

import org.springframework.http.ResponseEntity;

import com.sunic.user.rest.config.dto.BaseResponse;
import com.sunic.user.spec.facade.userworkspace.sdo.UserWorkspaceModifySdo;
import com.sunic.user.spec.facade.userworkspace.sdo.UserWorkspaceRegisterSdo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserWorkspace API", description = "workspace CRUD etc")
public interface UserWorkspaceResourceDocs {
    @Operation(summary = "register", description = "register API")
    ResponseEntity<BaseResponse> registerUserWorkspace(UserWorkspaceRegisterSdo userWorkspaceRegisterSdo);

    @Operation(summary = "retrieve", description = "retrieve API")
    ResponseEntity<BaseResponse> retrieveUserWorkspace(Integer id);

    @Operation(summary = "modify", description = "modify API")
    ResponseEntity<BaseResponse> modifyUserWorkspace(UserWorkspaceModifySdo userWorkspaceModifySdo);

    @Operation(summary = "delete", description = "delete API")
    ResponseEntity<BaseResponse> deleteUserWorkspace(Integer id);
}