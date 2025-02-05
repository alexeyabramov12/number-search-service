package com.example.number_search.controller;

import com.example.number_search.service.FileNumberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileNumberController.class)
class FileNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FileNumberService fileNumberService;

    @Test
    @DisplayName("Should return 200 OK when valid XLSX file and N are provided")
    void givenValidFilePathAndN_whenGetNthMax_thenReturnOk() throws Exception {
        when(fileNumberService.findNthMax("/data/numbers.xlsx", 3)).thenReturn(42);

        mockMvc.perform(get("/api/file/nth-max")
                        .param("filePath", "/data/numbers.xlsx")
                        .param("n", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("42"));

        verify(fileNumberService).findNthMax("/data/numbers.xlsx", 3);
    }

    @Test
    @DisplayName("Should return 400 Bad Request when file path is missing")
    void givenMissingFilePath_whenGetNthMax_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/file/nth-max")
                        .param("n", "3"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(fileNumberService);
    }

    @Test
    @DisplayName("Should return 400 Bad Request when file path is empty")
    void givenEmptyFilePath_whenGetNthMax_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/file/nth-max")
                        .param("filePath", "")
                        .param("n", "3"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(fileNumberService);
    }

    @Test
    @DisplayName("Should return 400 Bad Request when N is zero or negative")
    void givenInvalidN_whenGetNthMax_thenReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/file/nth-max")
                        .param("filePath", "/data/numbers.xlsx")
                        .param("n", "0"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/api/file/nth-max")
                        .param("filePath", "/data/numbers.xlsx")
                        .param("n", "-1"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(fileNumberService);
    }
}
