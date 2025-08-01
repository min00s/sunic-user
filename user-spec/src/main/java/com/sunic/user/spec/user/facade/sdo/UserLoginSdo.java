package com.sunic.user.spec.user.facade.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserLoginSdo {
    private String email;
    private String password;
}