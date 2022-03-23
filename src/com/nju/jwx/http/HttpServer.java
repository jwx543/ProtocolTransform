package com.nju.jwx.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(4567);
        while (true) {
            Socket client = server.accept();
            //读取请求数据
            InputStream input = client.getInputStream();
            HttpRequest request = ProtocolUtils.readRequest(input);
            System.out.println("收到的请求参数为: " + request.toString());
            OutputStream out = client.getOutputStream();
            //组装响应数据
            HttpResponse response = new HttpResponse();
            response.setEncode(Encode.UTF8);
            if("HELLO".equals(request.getCommand())){
                response.setResponse("hello");
            }else{
                response.setResponse("bye bye");
            }
            response.setResponseLength(response.getResponse().length());
            ProtocolUtils.writeResponse(out, response);
        }
    }
}
