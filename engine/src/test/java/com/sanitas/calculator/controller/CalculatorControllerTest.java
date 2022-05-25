package com.sanitas.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanitas.calculator.request.CalculatorRQ;
import com.sanitas.calculator.response.CalculatorRS;
import com.sanitas.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
class CalculatorControllerTest {

    public static final CalculatorRQ CALCULATOR_RQ = new CalculatorRQ(BigDecimal.TEN, BigDecimal.ONE);
    private MockMvc mockMvc;
    @Mock
    private CalculatorService calculatorService;
    @InjectMocks
    private CalculatorController calculatorController;

    private JacksonTester<CalculatorRQ> jacksonTester;

    @Test
    void add_two_numbers_OK() {
        final BigDecimal eleven = new BigDecimal(11);
        given(calculatorService.plus(CALCULATOR_RQ.getFirstNumber(), CALCULATOR_RQ.getSecondNumber()))
                .willReturn(eleven);

        final CalculatorRS plus = calculatorController.plus(CALCULATOR_RQ);
        assertThat(plus).isNotNull();
        assertThat(plus.getOperation()).isEqualTo("PLUS");
        assertThat(plus.getResult()).isEqualTo(eleven);
    }

    @Test
    void subtract_two_numbers_OK() {
        final BigDecimal nine = new BigDecimal(9);
        given(calculatorService.subtract(CALCULATOR_RQ.getFirstNumber(), CALCULATOR_RQ.getSecondNumber()))
                .willReturn(nine);

        final CalculatorRS subtract = calculatorController.subtract(CALCULATOR_RQ);
        assertThat(subtract).isNotNull();
        assertThat(subtract.getOperation()).isEqualTo("SUBTRACT");
        assertThat(subtract.getResult()).isEqualTo(nine);
    }

    @Test
    public void correct_call_plus_status_ok() throws Exception {
        this.setup();
        final MockHttpServletResponse response = mockMvc.perform(
                post("/api/calculator/plus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTester.write(CALCULATOR_RQ).getJson()
                        )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void sum_of_two_numbers_with_incorrect_url_not_found() throws Exception {
        this.setup();
        final MockHttpServletResponse response = mockMvc.perform(
                post("/api/plus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTester.write(CALCULATOR_RQ).getJson()
                        )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void correct_call_subtract_status_ok() throws Exception {
        this.setup();
        final MockHttpServletResponse response = mockMvc.perform(
                post("/api/calculator/subtract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTester.write(CALCULATOR_RQ).getJson()
                        )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void subtract_with_an_error_parameter_bad_request() throws Exception {
        this.setup();
        final MockHttpServletResponse response = mockMvc.perform(
                post("/api/calculator/subtract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jacksonTester.write(new CalculatorRQ(BigDecimal.TEN, null)).getJson()
                        )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(calculatorController)
                .build();
    }
}