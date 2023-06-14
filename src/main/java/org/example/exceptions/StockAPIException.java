package org.example.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class StockAPIException extends RuntimeException {

    @Getter
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public StockAPIException() {
        this("An error occurred!");
    }

    public StockAPIException(String message) {
        super(message);
    }

    public StockAPIException(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }
}
