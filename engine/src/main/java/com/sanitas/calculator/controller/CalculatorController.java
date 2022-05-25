package com.sanitas.calculator.controller;

import com.sanitas.calculator.request.CalculatorRQ;
import com.sanitas.calculator.response.CalculatorRS;
import com.sanitas.calculator.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/calculator")
@RequiredArgsConstructor
public class CalculatorController {

    private static final String PLUS = "PLUS";
    private static final String SUBTRACT = "SUBTRACT";
    private final CalculatorService calculatorService;

    @PostMapping(value = "/plus")
    public CalculatorRS plus(@RequestBody @Valid final CalculatorRQ calculatorRQ) {
        final BigDecimal plus = this.calculatorService.plus(calculatorRQ.getFirstNumber(),
                calculatorRQ.getSecondNumber());
        return new CalculatorRS(PLUS, plus);
    }

    @PostMapping(value = "/subtract")
    public CalculatorRS subtract(@RequestBody @Valid final CalculatorRQ calculatorRQ) {
        final BigDecimal subtract = this.calculatorService.subtract(calculatorRQ.getFirstNumber(),
                calculatorRQ.getSecondNumber());
        return new CalculatorRS(SUBTRACT, subtract);
    }
}
