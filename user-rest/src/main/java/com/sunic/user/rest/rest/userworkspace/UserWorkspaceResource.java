package com.sunic.user.rest.rest.userworkspace;

import com.sunic.user.aggregate.userworkspace.logic.UserWorkspaceLogic;
import com.sunic.user.spec.common.vo.BaseResponse;
import com.sunic.user.spec.facade.userworkspace.UserWorkspaceFacade;
import com.sunic.user.spec.facade.userworkspace.vo.UserWorkspaceCdo;
import com.sunic.user.spec.facade.userworkspace.vo.UserWorkspaceRdo;
import com.sunic.user.spec.facade.userworkspace.vo.UserWorkspaceUdo;
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
    public ResponseEntity<BaseResponse> registerUserWorkspace(@RequestBody UserWorkspaceCdo userWorkspaceRegisterSdo) {
        Integer workspaceId = userWorkspaceLogic.registerUserWorkspace(userWorkspaceRegisterSdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success", workspaceId), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> retrieveUserWorkspace(@PathVariable Integer id) {
        UserWorkspaceRdo workspace = userWorkspaceLogic.retrieveUserWorkspace(id);
        return new ResponseEntity<>(BaseResponse.from(true, "Success", workspace), HttpStatus.OK);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<BaseResponse> modifyUserWorkspace(@RequestBody UserWorkspaceUdo userWorkspaceModifySdo) {
        String result = userWorkspaceLogic.modifyUserWorkspace(userWorkspaceModifySdo);
        return new ResponseEntity<>(BaseResponse.from(true, "Success", result), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteUserWorkspace(@PathVariable Integer id) {
        userWorkspaceLogic.deleteUserWorkspace(id);
        return new ResponseEntity<>(BaseResponse.from(true, "Success"), HttpStatus.OK);
    }
}