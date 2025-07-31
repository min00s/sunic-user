package com.sunic.user.spec.facade.user;

import com.sunic.user.spec.facade.user.rdo.UserLoginRdo;
import com.sunic.user.spec.facade.user.sdo.UserJoinSdo;
import com.sunic.user.spec.facade.user.sdo.UserLoginSdo;
import com.sunic.user.spec.facade.user.sdo.UserRegisterSdo;

public interface UserFacade {
    void registerUser(UserRegisterSdo userRegisterSdo);
    UserLoginRdo loginUser(UserLoginSdo userLoginSdo);
    void deactivateUser();
    void joinWorkspace(UserJoinSdo userJoinSdo);
}