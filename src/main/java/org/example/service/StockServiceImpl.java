package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.data.dtos.requests.CreateUpdateStockRequest;
import org.example.data.dtos.responses.StockDTO;
import org.example.data.model.Stock;
import org.example.data.repository.StockRepository;
import org.example.exceptions.StockNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;

    @Override
    public List<StockDTO> getAllStocks() {
        List<Stock> allStocks = stockRepository.findAll();
        return allStocks.stream()
                .map(StockServiceImpl::mapStockToDto)
                .toList();
    }

    @Override
    public StockDTO findStockById(Long stockId) {
        Stock foundStock = stockRepository.findById(stockId).orElseThrow(() -> new StockNotFoundException("Stock with id " + stockId + " not found!"));
        return mapStockToDto(foundStock);
    }

    @Override
    public StockDTO updateStockById(Long stockId, CreateUpdateStockRequest updateStockRequest) {
        Stock stockToBeUpdated = stockRepository.findById(stockId).orElseThrow(() -> new StockNotFoundException("Stock with id " + stockId + " not found!"));
        stockToBeUpdated.setName(updateStockRequest.getName());
        stockToBeUpdated.setCurrentPrice(updateStockRequest.getCurrentPrice());
        stockToBeUpdated.setLastUpdate(LocalDateTime.now());
        Stock updatedStock = stockRepository.save(stockToBeUpdated);
        return mapStockToDto(updatedStock);
    }

    @Override
    public String deleteStockById(Long stockId) {
        Stock stockToBeDeleted = stockRepository.findById(stockId).orElseThrow(() -> new StockNotFoundException("Stock with id " + stockId + " not found!"));
        stockRepository.delete(stockToBeDeleted);
        return "SUCCESSFUL";
    }

    @Override
    public StockDTO createStock(CreateUpdateStockRequest createUpdateStockRequest) {
        Stock createdStock = Stock.builder()
                .name(createUpdateStockRequest.getName())
                .currentPrice(createUpdateStockRequest.getCurrentPrice())
                .createDate(LocalDateTime.now())
                .lastUpdate(LocalDateTime.now())
                .build();
        Stock savedStock = stockRepository.save(createdStock);
        return mapStockToDto(savedStock);
    }

    private static StockDTO mapStockToDto(Stock stock) {
        return StockDTO.builder()
                .id(stock.getId())
                .name(stock.getName())
                .currentPrice(stock.getCurrentPrice())
                .createDate(stock.getCreateDate())
                .lastUpdate(stock.getLastUpdate())
                .build();
    }
}
