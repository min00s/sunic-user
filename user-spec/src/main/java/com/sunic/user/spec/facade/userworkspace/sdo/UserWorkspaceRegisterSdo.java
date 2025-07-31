package com.sunic.user.spec.facade.userworkspace.sdo;

import com.sunic.user.spec.entity.UserWorkspaceType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserWorkspaceRegisterSdo {
    private String name;
    private String description;
    private UserWorkspaceType type;
    private Integer registrant;
}