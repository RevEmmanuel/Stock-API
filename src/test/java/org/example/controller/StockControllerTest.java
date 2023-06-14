package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.data.dtos.requests.CreateUpdateStockRequest;
import org.example.data.dtos.responses.StockDTO;
import org.example.service.StockService;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.Timeout;
import static org.mockito.BDDMockito.given;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.is;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
    }

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    void findAllStocks() throws Exception {
//        List<StockDTO> stocks = Arrays.asList(
//                new StockDTO(1L, "Stock1", 10.0, LocalDateTime.now(), LocalDateTime.now()),
//                new StockDTO(2L, "Stock2", 20.0, LocalDateTime.now(), LocalDateTime.now())
//        );
//
//        when(stockService.getAllStocks()).thenReturn(stocks);
//
//        verify(stockService, times(1)).getAllStocks();
//        verifyNoMoreInteractions(stockService);
    }

    @Test
    void getStockById(){

    }

    @Test
    void updateStockDetails() {
    }

    @Test
    void deleteStock() {
    }

    @Test
    void createStock() throws Exception {
        CreateUpdateStockRequest stockRequest = new CreateUpdateStockRequest();
        stockRequest.setName("Apple Inc.");
        stockRequest.setCurrentPrice(4500.78);
        given(stockService.createStock(stockRequest)).willReturn(StockDTO.builder().name("Apple Inc.").currentPrice(4500.78).build());
        mockMvc.perform(post("/api/v1/stocks/")
                        .content(objectMapper.writeValueAsString(stockRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()));

    }
}
