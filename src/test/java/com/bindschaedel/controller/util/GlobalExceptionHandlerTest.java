package com.bindschaedel.controller.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.TransactionSystemException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {
    private MockMvc mockMvc;

    @Mock
    private MockController mockController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(mockController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void transactionSystemExceptionsReturnBadRequestCode() throws Exception {
        when(mockController.dummy()).thenThrow(new TransactionSystemException("msg"));

        mockMvc.perform(get("/dummy")).andExpect(status().isBadRequest());
    }

    @Test
    public void numberFormatExceptionReturnBadRequestCode() throws Exception {
        when(mockController.dummy()).thenThrow(new NumberFormatException("msg"));

        mockMvc.perform(get("/dummy")).andExpect(status().isBadRequest());
    }
}
