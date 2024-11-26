package com.example.service_1;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcServer implements CommandLineRunner {

    private final ProductServiceImpl productService;

    public GrpcServer(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        Server server = ServerBuilder.forPort(9090)
                                     .addService(productService)
                                     .build();

        System.out.println("gRPC server started on port 9090...");
        server.start();
        server.awaitTermination();
    }
}