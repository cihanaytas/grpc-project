package com.example.product_service;

import com.example.ProductProto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final GrpcClient grpcClient;

    public ProductController(GrpcClient grpcClient) {
        this.grpcClient = grpcClient;
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductProto.Product product) {
        grpcClient.addProduct(product);
    }

    @GetMapping("/get")
    public String getProduct(@RequestParam String id) {
        return grpcClient.getProduct(id);
    }

    @GetMapping("/list")
    public void listProducts() {
        grpcClient.listProducts();
    }
}