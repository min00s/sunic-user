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

    public static UserWorkspace create(String name, String description, UserWorkspaceType type, 
                                     Integer registrant) {
        long currentTime = System.currentTimeMillis();
        return UserWorkspace.builder()
                .name(name)
                .description(description)
                .type(type)
                .state(UserWorkspaceState.Active)
                .registeredTime(currentTime)
                .registrant(registrant)
                .modifiedTime(currentTime)
                .modifier(registrant)
                .build();
    }

    public UserWorkspace modify(String name, String description, Integer modifier) {
        return UserWorkspace.builder()
                .id(this.id)
                .name(name)
                .description(description)
                .type(this.type)
                .state(this.state)
                .registeredTime(this.registeredTime)
                .registrant(this.registrant)
                .modifiedTime(System.currentTimeMillis())
                .modifier(modifier)
                .build();
    }

    public UserWorkspace changeState(UserWorkspaceState newState, Integer modifier) {
        return UserWorkspace.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .type(this.type)
                .state(newState)
                .registeredTime(this.registeredTime)
                .registrant(this.registrant)
                .modifiedTime(System.currentTimeMillis())
                .modifier(modifier)
                .build();
    }
}