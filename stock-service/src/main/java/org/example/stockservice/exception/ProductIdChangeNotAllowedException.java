package org.example.stockservice.exception;

/**
@author $(USER)    
**/public class ProductIdChangeNotAllowedException extends RuntimeException {
  public ProductIdChangeNotAllowedException(String message) {
    super(message);
  }
}
