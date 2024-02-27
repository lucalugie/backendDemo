package com.example.demo;

import com.example.demo.controller.BookGrpcController;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		var context = SpringApplication.run(DemoApplication.class, args);
		Server server = ServerBuilder
				.forPort(8081)
				.addService(context.getBean(BookGrpcController.class))
				.build();
		server.start();
		System.out.println("Server started on port " + server.getPort());
		server.awaitTermination();
	}

}
