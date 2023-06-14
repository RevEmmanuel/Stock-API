package org.example.exceptions;

import org.springframework.http.HttpStatus;

public class StockNotFoundException extends StockAPIException {

    public StockNotFoundException() {
        this("Stock not found!");
    }

    public StockNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
