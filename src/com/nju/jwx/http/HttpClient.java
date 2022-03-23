package com.nju.jwx.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// 客户端代码
public class HttpClient {
    public static void main(String[] args) throws IOException {
        // 请求
        HttpRequest request = new HttpRequest();
        request.setCommand("Hello");
        request.setCommandLength(request.getCommand().length());
        request.setEncode(Encode.UTF8);

        Socket client = new Socket("127.0.0.1", 4567);
        OutputStream out = client.getOutputStream();

        // 发送请求
        ProtocolUtils.writeRequest(out, request);

        //读取响应数据
        InputStream in = client.getInputStream();
        HttpResponse response = ProtocolUtils.readResponse(in);
        System.out.println("获取的响应结果信息为: " + response.toString());
    }
}
