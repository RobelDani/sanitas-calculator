package com.sanitas.calculator;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;

class SanitasCalculatorApplicationTests {

    @InjectMocks
    private SanitasCalculatorApplication sanitasCalculatorApplication;

    @Test
    void contextLoads() {

        final SanitasCalculatorApplication application = new SanitasCalculatorApplication();

        assertThat(application).isNotNull();
    }
}
