package com.sanitas.calculator.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    String fieldName;
    String message;

    public ErrorResponse(final String fieldName, final String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
