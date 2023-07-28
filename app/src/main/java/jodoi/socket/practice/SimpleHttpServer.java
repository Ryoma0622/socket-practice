package jodoi.socket.practice;

import java.io.*;
import java.net.*;
import java.util.Optional;

public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {

        ControllerScanner scanner = new ControllerScanner("jodoi.socket.practice.controller");

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is listening on port 8080");
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    line = in.readLine();
                    System.out.println(line);
                    String method = line.split(" ")[0];
                    String path = line.split(" ")[1];

                    Optional<Handler> optionalHandler = scanner.getHandler(method, path);

                    // read the rest of the lines (headers and body) without parsing
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                        if (line.isEmpty()) {
                            break;
                        }
                    }

                    // handle the request
                    if (optionalHandler.isPresent()) {
                        Handler handler = optionalHandler.get();
                        handler.getMethod().invoke(handler.getController(), socket.getOutputStream());
                    } else {
                        String response = "HTTP/1.1 404 Not Found\r\n\r\nPage Not Found";
                        socket.getOutputStream().write(response.getBytes("UTF-8"));
                    }
                }
            }
        }
    }
}
