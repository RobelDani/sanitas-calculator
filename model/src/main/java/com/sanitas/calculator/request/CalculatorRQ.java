package com.sanitas.calculator.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode
public class CalculatorRQ implements Serializable {

    private static final long serialVersionUID = 8156240072747693150L;

    @NotNull(message = "firstNumber missing or invalid")
    BigDecimal firstNumber;
    @NotNull(message = "secondNumber missing or invalid")
    BigDecimal secondNumber;

    @JsonCreator
    public CalculatorRQ(@JsonProperty("firstNumber") @NotNull final BigDecimal firstNumber,
                        @JsonProperty("secondNumber") @NotNull final BigDecimal secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }
}
