package org.example.stockservice.exception;

/**
 * @author $(USER)
 **/
public class StockNotFound extends RuntimeException {
    public StockNotFound(String message) {
        super(message);
    }
}
