package com.example.product_service;

import com.example.ProductProto;
import com.example.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcClient {

    private final ManagedChannel channel;
    private final ProductServiceGrpc.ProductServiceBlockingStub stub;

    public GrpcClient() {
        this.channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                                           .usePlaintext()
                                           .build();
        this.stub = ProductServiceGrpc.newBlockingStub(channel);
    }

    public void addProduct(ProductProto.Product product) {
        ProductProto.ProductRequest request = ProductProto.ProductRequest.newBuilder()
                                               .setProduct(product)
                                               .build();
        ProductProto.ProductResponse response = stub.addProduct(request);
        System.out.println("Product added: " + response.getProduct().getName());
    }

    public String getProduct(String productId) {
        ProductProto.ProductIdRequest request = ProductProto.ProductIdRequest.newBuilder()
                                                   .setId(productId)
                                                   .build();
        ProductProto.ProductResponse response = stub.getProduct(request);
        System.out.println("Product found: " + response.getProduct().getName());
        return response.getProduct().getName();
    }

    public void listProducts() {
        ProductProto.Empty request = ProductProto.Empty.newBuilder().build();
        ProductProto.ProductListResponse response = stub.listProducts(request);
        response.getProductsList().forEach(product -> System.out.println("Product: " + product.getName()));
    }
}