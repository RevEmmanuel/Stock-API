package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.data.dtos.requests.CreateUpdateStockRequest;
import org.example.data.dtos.responses.StockDTO;
import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @Operation(summary = "Get all stocks",
            description = "Returns a Response entity containing all existing stocks in a List.")
    @GetMapping("/")
    public ResponseEntity<List<StockDTO>> findAllStocks() {
        List<StockDTO> stocks = stockService.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @Operation(summary = "Get A Particular Stock by the stock's id",
            description = "Returns a Response entity containing the requested stock and HTTP status code. If the stock is not found, an exception is thrown.")
    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(
            @PathVariable
            @Parameter(name = "id", description = "The id of the required stock",
                    required = true, example = "1L") @Valid @NotNull
            Long id) {
        StockDTO foundStock = stockService.findStockById(id);
        return new ResponseEntity<>(foundStock, HttpStatus.OK);
    }

    @Operation(summary = "Update the details of an existing stock",
            description = "Returns a Response entity containing the updated stock's details and HTTP status code.\nIt takes in the id of the stock to be updated as well as an UpdateStockRequest containing the details to be updated in the stock.")
    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStockDetails(
            @PathVariable
            @Parameter(name = "id", description = "The id of the required stock",
                    required = true, example = "1L") @Valid @NotNull
            Long id,
            @RequestBody
            @Parameter(name = "updateStockRequest", required = true,
                    description = "Contains the details of the stock that will be updated.")
            @Valid
            CreateUpdateStockRequest updateStockRequest) {
        StockDTO updateStockDetailsResponse = stockService.updateStockById(id, updateStockRequest);
        return new ResponseEntity<>(updateStockDetailsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete an existing stock",
            description = "Returns a Response entity HTTP status code")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(
            @PathVariable
            @Parameter(name = "id", required = true, example = "1L",
                    description = "The Id of the stock to be deleted")
            @Valid @NotNull(message = "Input cannot be null")
            Long id) {
        String message = stockService.deleteStockById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "Create a new stock",
            description = "Returns a Response entity containing the new stock's details and HTTP status code.\nIt takes in a request body that contains the details required to create a new stock.")
    @PostMapping("/")
    public ResponseEntity<StockDTO> createStock(
            @RequestBody
            @Parameter(name = "CreateUpdateStockRequest", required = true,
                    description = "Contains the details required to create a new stock which is the name of the new stock and it's current price.")
            @Valid CreateUpdateStockRequest stockRequest) {
        StockDTO response = stockService.createStock(stockRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
