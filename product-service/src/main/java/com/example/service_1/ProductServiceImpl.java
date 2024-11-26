package com.example.service_1;

import com.example.ProductProto;
import com.example.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private final List<ProductProto.Product> productList = new ArrayList<>();

    @Override
    public void addProduct(ProductProto.ProductRequest request, StreamObserver<ProductProto.ProductResponse> responseObserver) {
        ProductProto.Product product = request.getProduct();
        productList.add(product);

        ProductProto.ProductResponse response = ProductProto.ProductResponse.newBuilder()
                .setProduct(product)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductProto.ProductIdRequest request, StreamObserver<ProductProto.ProductResponse> responseObserver) {
        String productId = request.getId();

        ProductProto.Product product = ProductProto.Product.newBuilder().
                setDescription("desc")
                .setName("name")
                .setId(request.getId())
                .build();


        ProductProto.ProductResponse response = ProductProto.ProductResponse.newBuilder()
                .setProduct(product)
                .build();
        responseObserver.onNext(response);


        responseObserver.onCompleted();
    }

    @Override
    public void listProducts(ProductProto.Empty request, StreamObserver<ProductProto.ProductListResponse> responseObserver) {
        ProductProto.ProductListResponse response = ProductProto.ProductListResponse.newBuilder()
                .addAllProducts(productList)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}