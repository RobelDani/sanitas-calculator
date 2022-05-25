package com.sanitas.calculator.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculatorRS implements Serializable {

    private static final long serialVersionUID = -4028029070879238482L;
    String operation;
    BigDecimal result;

    @JsonCreator
    public CalculatorRS(@JsonProperty("operation") final String operation,
                        @JsonProperty("result") final BigDecimal result) {
        this.operation = operation;
        this.result = result;
    }
}
