package com.sunic.user.spec.user.facade.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserRegisterSdo {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String birthYear;
    private Integer gender;
}