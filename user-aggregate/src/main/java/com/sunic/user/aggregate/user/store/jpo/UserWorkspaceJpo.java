package com.sunic.user.aggregate.user.store.jpo;

import com.sunic.user.spec.userworkspace.entity.UserWorkspace;
import com.sunic.user.spec.userworkspace.entity.UserWorkspaceState;
import com.sunic.user.spec.userworkspace.entity.UserWorkspaceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_workspace")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class UserWorkspaceJpo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private UserWorkspaceType type;

    @Enumerated(EnumType.STRING)
    private UserWorkspaceState state;

    private Long registeredTime;
    private Integer registrant;
    private Long modifiedTime;
    private Integer modifier;

    public static UserWorkspaceJpo from(UserWorkspace userWorkspace) {
        return UserWorkspaceJpo.builder()
                .id(userWorkspace.getId())
                .name(userWorkspace.getName())
                .description(userWorkspace.getDescription())
                .type(userWorkspace.getType())
                .state(userWorkspace.getState())
                .registeredTime(userWorkspace.getRegisteredTime())
                .registrant(userWorkspace.getRegistrant())
                .modifiedTime(userWorkspace.getModifiedTime())
                .modifier(userWorkspace.getModifier())
                .build();
    }

    public UserWorkspace toEntity() {
        return UserWorkspace.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .type(this.type)
                .state(this.state)
                .registeredTime(this.registeredTime)
                .registrant(this.registrant)
                .modifiedTime(this.modifiedTime)
                .modifier(this.modifier)
                .build();
    }
}