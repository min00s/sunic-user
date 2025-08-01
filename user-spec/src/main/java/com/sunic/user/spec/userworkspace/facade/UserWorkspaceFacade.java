package com.sunic.user.spec.userworkspace.facade;

import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceCdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceUdo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "UserWorkspace API", description = "workspace CRUD etc")
public interface UserWorkspaceFacade {
    @Operation(summary = "register", description = "register API")
    ResponseEntity<com.sunic.user.spec.common.ApiResponse> registerUserWorkspace(UserWorkspaceCdo userWorkspaceRegisterSdo);

    @Operation(summary = "retrieve", description = "retrieve API")
    ResponseEntity<com.sunic.user.spec.common.ApiResponse> retrieveUserWorkspace(Integer id);

    @Operation(summary = "modify", description = "modify API")
    ResponseEntity<com.sunic.user.spec.common.ApiResponse> modifyUserWorkspace(UserWorkspaceUdo userWorkspaceModifySdo);

    @Operation(summary = "delete", description = "delete API")
    ResponseEntity<com.sunic.user.spec.common.ApiResponse> deleteUserWorkspace(Integer id);
}