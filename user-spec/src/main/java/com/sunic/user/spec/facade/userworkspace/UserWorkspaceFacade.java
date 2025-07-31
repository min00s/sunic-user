package com.sunic.user.spec.facade.userworkspace;

import com.sunic.user.spec.facade.userworkspace.rdo.UserWorkspaceRdo;
import com.sunic.user.spec.facade.userworkspace.sdo.UserWorkspaceModifySdo;
import com.sunic.user.spec.facade.userworkspace.sdo.UserWorkspaceRegisterSdo;

public interface UserWorkspaceFacade {
    Integer registerUserWorkspace(UserWorkspaceRegisterSdo userWorkspaceRegisterSdo);
    UserWorkspaceRdo retrieveUserWorkspace(Integer id);
    String modifyUserWorkspace(UserWorkspaceModifySdo userWorkspaceModifySdo);
    void deleteUserWorkspace(Integer id);
}