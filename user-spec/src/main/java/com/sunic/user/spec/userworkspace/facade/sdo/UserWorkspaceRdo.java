package com.sunic.user.spec.userworkspace.facade.sdo;

import com.sunic.user.spec.userworkspace.entity.UserWorkspaceState;
import com.sunic.user.spec.userworkspace.entity.UserWorkspaceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserWorkspaceRdo {
    private Integer id;
    private String name;
    private String description;
    private UserWorkspaceType type;
    private UserWorkspaceState state;
    private Long registeredTime;
    private Integer registrant;
    private Long modifiedTime;
    private Integer modifier;
}