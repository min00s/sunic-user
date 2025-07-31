package com.sunic.user.spec.facade.userworkspace;

import com.sunic.user.spec.common.vo.BaseResponse;
import com.sunic.user.spec.facade.userworkspace.vo.UserWorkspaceCdo;
import com.sunic.user.spec.facade.userworkspace.vo.UserWorkspaceUdo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "UserWorkspace API", description = "workspace CRUD etc")
public interface UserWorkspaceFacade {
    @Operation(summary = "register", description = "register API")
    ResponseEntity<BaseResponse> registerUserWorkspace(UserWorkspaceCdo userWorkspaceRegisterSdo);

    @Operation(summary = "retrieve", description = "retrieve API")
    ResponseEntity<BaseResponse> retrieveUserWorkspace(Integer id);

    @Operation(summary = "modify", description = "modify API")
    ResponseEntity<BaseResponse> modifyUserWorkspace(UserWorkspaceUdo userWorkspaceModifySdo);

    @Operation(summary = "delete", description = "delete API")
    ResponseEntity<BaseResponse> deleteUserWorkspace(Integer id);
}