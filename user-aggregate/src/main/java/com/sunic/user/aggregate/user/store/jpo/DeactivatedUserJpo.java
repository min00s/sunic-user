package com.sunic.user.aggregate.user.store.jpo;

import com.sunic.user.spec.facade.user.entity.DeactivatedUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "deactivated_user")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class DeactivatedUserJpo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String name;
    private String password;
    private String phone;
    private String birthYear;
    private Integer gender;

    @ManyToMany
    private List<UserWorkspaceJpo> userWorkspaces;

    private Integer loginFailCount;

    public static DeactivatedUserJpo from(DeactivatedUser deactivatedUser) {
        return DeactivatedUserJpo.builder()
                .email(deactivatedUser.getEmail())
                .name(deactivatedUser.getName())
                .password(deactivatedUser.getPassword())
                .phone(deactivatedUser.getPhone())
                .birthYear(deactivatedUser.getBirthYear())
                .gender(deactivatedUser.getGender())
                .loginFailCount(deactivatedUser.getLoginFailCount())
                .build();
    }
}