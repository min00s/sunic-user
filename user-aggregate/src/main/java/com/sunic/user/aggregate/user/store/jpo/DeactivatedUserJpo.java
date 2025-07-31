package com.sunic.user.aggregate.user.store.jpo;

import java.util.List;

import com.sunic.user.spec.entity.DeactivatedUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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