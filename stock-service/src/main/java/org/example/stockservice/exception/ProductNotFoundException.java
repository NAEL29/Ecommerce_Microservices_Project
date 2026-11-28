package org.example.stockservice.exception;

/**
@author $(USER)    
**/public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(String message) {
    super(message);
  }
}
