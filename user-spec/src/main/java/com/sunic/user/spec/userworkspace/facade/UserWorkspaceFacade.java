package com.sunic.user.spec.userworkspace.facade;

import com.sunic.user.spec.common.CommonResponse;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceCdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceUdo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "UserWorkspace API", description = "workspace CRUD etc")
public interface UserWorkspaceFacade {
    @Operation(summary = "register", description = "register API")
    ResponseEntity<CommonResponse> registerUserWorkspace(UserWorkspaceCdo userWorkspaceRegisterSdo);

    @Operation(summary = "retrieve", description = "retrieve API")
    ResponseEntity<CommonResponse> retrieveUserWorkspace(Integer id);

    @Operation(summary = "modify", description = "modify API")
    ResponseEntity<CommonResponse> modifyUserWorkspace(UserWorkspaceUdo userWorkspaceModifySdo);

    @Operation(summary = "delete", description = "delete API")
    ResponseEntity<CommonResponse> deleteUserWorkspace(Integer id);
}