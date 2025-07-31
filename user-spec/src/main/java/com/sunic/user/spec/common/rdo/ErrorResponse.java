package com.sunic.user.spec.common.rdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class ErrorResponse {
    private boolean success;
    private String message;

    public static ErrorResponse from(boolean success, String message) {
        return builder().success(success).message(message).build();
    }
}