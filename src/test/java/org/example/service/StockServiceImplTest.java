package org.example.service;

import org.example.data.dtos.requests.CreateUpdateStockRequest;
import org.example.data.dtos.responses.StockDTO;
import org.example.exceptions.StockNotFoundException;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class StockServiceImplTest {

    @Autowired
    private StockService stockService;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @BeforeEach
    @AfterEach
    void testThatDbIsEmpty() {
        stockService.deleteAll();
        List<StockDTO> responses = stockService.getAllStocks();
        assert(responses).isEmpty();
    }

    @Test
    void createStock() {
        CreateUpdateStockRequest request = CreateUpdateStockRequest.builder()
                .name("Stock Initial")
                .currentPrice(12953.04)
                .build();
        StockDTO response = stockService.createStock(request);
        assertNotNull(response);
        assertEquals("Stock Initial", response.getName());
        assertEquals(12953.04, response.getCurrentPrice());
    }

    @Test
    void createMultipleStock() {
        SecureRandom random = new SecureRandom();
        int counter = 0;
        while (counter <= 10) {
            Double price = 0.5 * random.nextDouble();
            CreateUpdateStockRequest request = CreateUpdateStockRequest.builder()
                    .name("Stock " + counter)
                    .currentPrice(price)
                    .build();
            StockDTO response = stockService.createStock(request);
            assertNotNull(response);
            assertEquals("Stock " + counter, response.getName());
            assertEquals(price, response.getCurrentPrice());
            counter++;
        }
    }

    @Test
    void testToFindStockById() {
        CreateUpdateStockRequest request = CreateUpdateStockRequest.builder()
                .name("Stock Initial")
                .currentPrice(12953.04)
                .build();
        StockDTO response = stockService.createStock(request);
        assertNotNull(response);
        assertEquals("Stock Initial", response.getName());
        assertEquals(12953.04, response.getCurrentPrice());

        StockDTO queryResponse = stockService.findStockById(response.getId());
        assertNotNull(queryResponse);
        assertEquals(queryResponse.getName(), response.getName());
        assertEquals(queryResponse.getCurrentPrice(), response.getCurrentPrice());
    }

    @Test
    void updateStockById() {
        CreateUpdateStockRequest request = CreateUpdateStockRequest.builder()
                .name("Stock Initial")
                .currentPrice(12953.04)
                .build();
        StockDTO response = stockService.createStock(request);
        assertNotNull(response);
        assertEquals("Stock Initial", response.getName());
        assertEquals(12953.04, response.getCurrentPrice());

        StockDTO queryResponse = stockService.findStockById(response.getId());
        assertNotNull(queryResponse);
        assertEquals(queryResponse.getName(), response.getName());
        assertEquals(queryResponse.getCurrentPrice(), response.getCurrentPrice());
        LocalDateTime lastUpdatedTime = queryResponse.getLastUpdate();

        CreateUpdateStockRequest requestToUpdate = CreateUpdateStockRequest.builder()
                .name("Apple Shares")
                .currentPrice(159053.06)
                .build();
        StockDTO updatedStock = stockService.updateStockById(queryResponse.getId(), requestToUpdate);
        assertNotEquals(queryResponse, updatedStock);

        queryResponse = stockService.findStockById(response.getId());
        assertEquals(queryResponse.getId(), updatedStock.getId());
        assertEquals("Apple Shares", queryResponse.getName());
        assertEquals(159053.06, queryResponse.getCurrentPrice());
        assertNotEquals(lastUpdatedTime, queryResponse.getLastUpdate());
    }

    @Test
    void deleteStockById() {
        CreateUpdateStockRequest request = CreateUpdateStockRequest.builder()
                .name("Stock Initial")
                .currentPrice(12953.04)
                .build();
        StockDTO response = stockService.createStock(request);
        assertNotNull(response);
        String message = stockService.deleteStockById(response.getId());
        assertEquals("SUCCESSFUL", message);
        assertThrows(StockNotFoundException.class, () -> stockService.findStockById(response.getId()));
    }

}