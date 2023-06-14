package org.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StockAPIExceptionHandler {

    @ExceptionHandler(StockAPIException.class)
    public ResponseEntity<StockAPIExceptionResponse> handleStockAPIApplicationException(StockAPIException e){
        var res = StockAPIExceptionResponse
                .builder()
                .message(e.getMessage())
                .status(e.getStatus())
                .build();

        return new ResponseEntity<>(res, e.getStatus());
    }
}
