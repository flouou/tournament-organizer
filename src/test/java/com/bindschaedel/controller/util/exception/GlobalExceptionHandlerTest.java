package com.bindschaedel.controller.util.exception;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.TransactionSystemException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {
    private static final Map<String, String> ENCODED_ERROR = buildEncodedError();

    private MockMvc mockMvc;
    @Mock
    private MockController mockController;
    @Mock
    private ErrorEncoder errorEncoder;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(mockController)
                .setControllerAdvice(new GlobalExceptionHandler(errorEncoder))
                .build();
    }

    @Test
    public void transactionSystemExceptionsInvokeEncoder() throws Exception {
        TransactionSystemException ex = new TransactionSystemException("msg");
        when(mockController.dummy()).thenThrow(ex);
        when(errorEncoder.encode(ex)).thenReturn(ENCODED_ERROR);

        MvcResult mvcResult = hitDummyEndpoint().andReturn();
        assertResponseContent(mvcResult);
    }

    @Test
    public void numberFormatExceptionsInvokeEncoder() throws Exception {
        NumberFormatException ex = new NumberFormatException("msg");
        when(mockController.dummy()).thenThrow(ex);
        when(errorEncoder.encode(ex)).thenReturn(ENCODED_ERROR);

        MvcResult mvcResult = hitDummyEndpoint().andReturn();
        assertResponseContent(mvcResult);
    }

    @Test
    public void transactionSystemExceptionsReturnBadRequestCode() throws Exception {
        when(mockController.dummy()).thenThrow(new TransactionSystemException("msg"));

        hitDummyEndpoint().andExpect(status().isBadRequest());
    }

    @Test
    public void numberFormatExceptionReturnBadRequestCode() throws Exception {
        when(mockController.dummy()).thenThrow(new NumberFormatException("msg"));

        hitDummyEndpoint().andExpect(status().isBadRequest());
    }

    private ResultActions hitDummyEndpoint() throws Exception {
        return mockMvc.perform(get("/dummy"));
    }

    private static void assertResponseContent(MvcResult r) throws UnsupportedEncodingException {
        String expected = new JSONObject(ENCODED_ERROR).toString();
        assertThat(r.getResponse().getContentAsString()).isEqualTo(expected);
    }

    private static Map<String, String> buildEncodedError() {
        Map<String, String> ee = new HashMap<>();
        ee.put("error", "errorMsg");
        return ee;
    }

}
