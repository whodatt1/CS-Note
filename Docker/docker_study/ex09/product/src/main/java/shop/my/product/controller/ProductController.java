package shop.my.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.my.product.model.Product;
import shop.my.product.model.ProductRepository;

// nginx 에서 프록시 패스가 되기 때문에 주석처리 nginx에서의 요청은 자바스크립트 요청이 아님
//@CrossOrigin(origins = "http://localhost:3000") // 해당 포트 자바스크립트 요청 허용
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
