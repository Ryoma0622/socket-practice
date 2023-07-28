package jodoi.socket.practice.controller;

import jodoi.socket.practice.annotation.Controller;
import jodoi.socket.practice.annotation.httpmethod.Get;

import java.io.IOException;
import java.io.OutputStream;

@Controller("/hoge")
public class HogeController {

    @Get("/home")
    public void home(OutputStream out) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n\r\nWelcome to home!";
        out.write(response.getBytes("UTF-8"));
    }

    @Get("/init")
    public void init(OutputStream out) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n\r\nWelcome to home!";
        out.write(response.getBytes("UTF-8"));
    }
}
