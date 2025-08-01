package com.sunic.user.rest.rest.userworkspace;

import com.sunic.user.aggregate.userworkspace.logic.UserWorkspaceLogic;
import com.sunic.user.spec.userworkspace.facade.UserWorkspaceFacade;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceCdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceRdo;
import com.sunic.user.spec.userworkspace.facade.sdo.UserWorkspaceUdo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workspace")
@RequiredArgsConstructor
public class UserWorkspaceResource implements UserWorkspaceFacade {

    private final UserWorkspaceLogic userWorkspaceLogic;

    @Override
    @PostMapping("/")
    public ResponseEntity<com.sunic.user.spec.common.ApiResponse> registerUserWorkspace(@RequestBody UserWorkspaceCdo userWorkspaceRegisterSdo) {
        Integer workspaceId = userWorkspaceLogic.registerUserWorkspace(userWorkspaceRegisterSdo);
        return new ResponseEntity<>(com.sunic.user.spec.common.ApiResponse.from(true, "Success", workspaceId), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<com.sunic.user.spec.common.ApiResponse> retrieveUserWorkspace(@PathVariable Integer id) {
        UserWorkspaceRdo workspace = userWorkspaceLogic.retrieveUserWorkspace(id);
        return new ResponseEntity<>(com.sunic.user.spec.common.ApiResponse.from(true, "Success", workspace), HttpStatus.OK);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<com.sunic.user.spec.common.ApiResponse> modifyUserWorkspace(@RequestBody UserWorkspaceUdo userWorkspaceModifySdo) {
        String result = userWorkspaceLogic.modifyUserWorkspace(userWorkspaceModifySdo);
        return new ResponseEntity<>(com.sunic.user.spec.common.ApiResponse.from(true, "Success", result), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<com.sunic.user.spec.common.ApiResponse> deleteUserWorkspace(@PathVariable Integer id) {
        userWorkspaceLogic.deleteUserWorkspace(id);
        return new ResponseEntity<>(com.sunic.user.spec.common.ApiResponse.from(true, "Success"), HttpStatus.OK);
    }
}