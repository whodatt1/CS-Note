package shop.my.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.my.product.model.Product;
import shop.my.product.model.ProductRepository;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductRepository productRepository;
    
    @GetMapping("/products")
    public ResponseEntity<?> products() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    
}
