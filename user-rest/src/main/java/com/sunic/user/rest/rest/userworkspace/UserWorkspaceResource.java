package com.sunic.user.rest.rest.userworkspace;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunic.user.spec.common.rdo.BaseResponse;
import com.sunic.user.rest.rest.userworkspace.docs.UserWorkspaceResourceDocs;
import com.sunic.user.spec.facade.userworkspace.UserWorkspaceFacade;
import com.sunic.user.spec.facade.userworkspace.rdo.UserWorkspaceRdo;
import com.sunic.user.spec.facade.userworkspace.sdo.UserWorkspaceModifySdo;
import com.sunic.user.spec.facade.userworkspace.sdo.UserWorkspaceRegisterSdo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workspace")
@RequiredArgsConstructor
public class UserWorkspaceResource implements UserWorkspaceResourceDocs {

    private final UserWorkspaceFacade userWorkspaceFacade;

    @Override
    @PostMapping("/")
    public ResponseEntity<BaseResponse> registerUserWorkspace(@RequestBody UserWorkspaceRegisterSdo userWorkspaceRegisterSdo) {
        Integer workspaceId = userWorkspaceFacade.registerUserWorkspace(userWorkspaceRegisterSdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success", workspaceId), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> retrieveUserWorkspace(@PathVariable Integer id) {
        UserWorkspaceRdo workspace = userWorkspaceFacade.retrieveUserWorkspace(id);
        return new ResponseEntity<>(BaseResponse.from(true, "Success", workspace), HttpStatus.OK);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<BaseResponse> modifyUserWorkspace(@RequestBody UserWorkspaceModifySdo userWorkspaceModifySdo) {
        String result = userWorkspaceFacade.modifyUserWorkspace(userWorkspaceModifySdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success", result), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteUserWorkspace(@PathVariable Integer id) {
        userWorkspaceFacade.deleteUserWorkspace(id);
        return new ResponseEntity<>(BaseResponse.from(true, "Success"), HttpStatus.OK);
    }
}