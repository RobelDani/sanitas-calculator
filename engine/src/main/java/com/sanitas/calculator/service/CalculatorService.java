package com.sanitas.calculator.service;

import java.math.BigDecimal;

public interface CalculatorService {

    BigDecimal plus(BigDecimal firstNumber, BigDecimal secondNumber);

    BigDecimal subtract(BigDecimal firstNumber, BigDecimal secondNumber);
}
