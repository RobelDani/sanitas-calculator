package com.sanitas.calculator.service;

import com.sanitas.calculator.service.impl.CalculatorServiceImpl;
import io.corp.calculator.TracerAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceImplTest {

    public static final BigDecimal FIRST_NUMBER = BigDecimal.TEN;
    public static final BigDecimal SECOND_NUMBER = BigDecimal.ONE;

    @Mock
    private TracerAPI tracerAPI;
    @InjectMocks
    private CalculatorServiceImpl calculatorServiceImpl;

    @Test
    void add_two_numbers_success() {
        doNothing().when(tracerAPI).trace(anyString());
        final BigDecimal plus = calculatorServiceImpl.plus(FIRST_NUMBER, SECOND_NUMBER);
        assertThat(plus).isNotNull();
        assertThat(plus).isEqualTo(new BigDecimal(11));
    }

    @Test
    void subtract_two_numbers_success() {
        doNothing().when(tracerAPI).trace(anyString());
        final BigDecimal subtract = calculatorServiceImpl.subtract(FIRST_NUMBER, SECOND_NUMBER);
        assertThat(subtract).isNotNull();
        assertThat(subtract).isEqualTo(new BigDecimal(9));
    }
}