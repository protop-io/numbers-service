package awesomelabs.numbers;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class App {

    private final int port;
    private final Server server;

    private App(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new MoneyService())
                .build();
    }

    public static void main(String... args) {
        App app = new App(8080);
        app.start();
    }

    private void start() {
        try {
            server.start();
        } catch (IOException e) {
            System.out.println("Failed to start server: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server since JVM is shutting down");
            App.this.stop();
        }));

        System.out.println("Server started on port " + port);

        blockUntilShutdown();
    }

    private void stop() {
        server.shutdown();
        System.out.println("Server successfully shut down");
    }

    private void blockUntilShutdown() {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            System.out.println("Unable to block thread: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
