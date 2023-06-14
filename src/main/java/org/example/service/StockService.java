package org.example.service;

import org.example.data.dtos.requests.CreateUpdateStockRequest;
import org.example.data.dtos.responses.StockDTO;
import java.util.List;

public interface StockService {

    List<StockDTO> getAllStocks();

    StockDTO findStockById(Long stockId);

    StockDTO updateStockById(Long stockId, CreateUpdateStockRequest updateStockRequest);

    String deleteStockById(Long stockId);

    StockDTO createStock(CreateUpdateStockRequest createUpdateStockRequest);

    void deleteAll();
}
