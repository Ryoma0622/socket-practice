package jodoi.socket.practice.controller;

import jodoi.socket.practice.annotation.Controller;
import jodoi.socket.practice.annotation.httpmethod.Get;
import jodoi.socket.practice.annotation.httpmethod.Post;
import jodoi.socket.practice.annotation.httpmethod.Put;

import java.io.IOException;
import java.io.OutputStream;

@Controller("/api")
public class SimpleController {

    @Get("/home")
    public void home(OutputStream out) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n\r\nWelcome to home!";
        out.write(response.getBytes("UTF-8"));
    }

    @Get("/init")
    public void getinit(OutputStream out) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n\r\nWelcome to home!";
        out.write(response.getBytes("UTF-8"));
    }

    @Put("/init")
    public void putinit(OutputStream out) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n\r\nWelcome to home!";
        out.write(response.getBytes("UTF-8"));
    }

    @Post("/init")
    public void postinit(OutputStream out) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n\r\nWelcome to home!";
        out.write(response.getBytes("UTF-8"));
    }
}
