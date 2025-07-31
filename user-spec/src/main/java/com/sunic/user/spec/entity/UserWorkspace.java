package com.sunic.user.spec.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder  
@AllArgsConstructor
public class UserWorkspace {
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