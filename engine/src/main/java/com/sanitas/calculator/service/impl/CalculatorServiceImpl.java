package com.sanitas.calculator.service.impl;

import com.sanitas.calculator.service.CalculatorService;
import io.corp.calculator.TracerAPI;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private static final String SUBTRACT = "subtract";
    private static final String ADD = "add";
    private final TracerAPI tracerAPI;

    public CalculatorServiceImpl(final TracerAPI tracerAPI) {
        this.tracerAPI = tracerAPI;
    }

    @Override
    public BigDecimal plus(final BigDecimal firstNumber, final BigDecimal secondNumber) {
        this.startTrace(ADD, firstNumber, secondNumber, "+");

        final BigDecimal plus = firstNumber.add(secondNumber);
        this.endTrace(ADD, plus);
        return plus;
    }

    @Override
    public BigDecimal subtract(final BigDecimal firstNumber, final BigDecimal secondNumber) {
        this.startTrace(SUBTRACT, firstNumber, secondNumber, "-");

        final BigDecimal subtract = firstNumber.subtract(secondNumber);
        this.endTrace(SUBTRACT, subtract);
        return subtract;
    }

    private void startTrace(final String operation, final BigDecimal firstNumber,
                            final BigDecimal secondNumber, final String symbol) {
        tracerAPI.trace(String.format("Start of the %s of numbers", operation));
        tracerAPI.trace(String.format("Input values: %s %s %s", firstNumber, symbol, secondNumber));
    }

    private void endTrace(final String operation, final BigDecimal result) {
        tracerAPI.trace(String.format("End of %s operation", operation));
        tracerAPI.trace(String.format("The value obtained in the operation was %s", result));
    }
}
