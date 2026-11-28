package org.example.stockservice.exception;

/**
@author $(USER)    
**/public class DuplicateStockException extends RuntimeException {
  public DuplicateStockException(String message) {
    super(message);
  }
}
