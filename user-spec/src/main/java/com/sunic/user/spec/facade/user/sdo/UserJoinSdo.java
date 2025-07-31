package com.sunic.user.spec.facade.user.sdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserJoinSdo {
    private Integer userId;
    private Integer workspaceId;
}