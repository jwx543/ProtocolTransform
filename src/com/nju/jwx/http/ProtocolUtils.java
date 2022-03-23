package com.nju.jwx.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// 协议工具类
public final class ProtocolUtils {

    // 从输入流中反序列化Request对象
    public static HttpRequest readRequest(InputStream input) throws IOException {
        // 读取编码
        byte[] encodeByte = new byte[1];
        input.read(encodeByte);
        byte encode = encodeByte[0];

        // 读取命令长度
        byte[] commandLengthBytes = new byte[4];
        input.read(commandLengthBytes);
        int commandLength = ByteUtils.byte2Int(commandLengthBytes);

        // 读取命令
        byte[] commandBytes = new byte[commandLength];
        input.read(commandBytes);
        String command = "";
        if(Encode.UTF8 == encode){
            command = new String(commandBytes, "UTF-8");
        }else if(Encode.GBK == encode){
            command = new String(commandBytes, "GBK");
        }

        // 返回结果
        HttpRequest request = new HttpRequest(encode, command, commandLength);
        return request;
    }

    // 从输入流中反序列化Response对象
    public static HttpResponse readResponse(InputStream input) throws IOException{
        //读取编码
        byte[] encodeByte = new byte[1];
        input.read(encodeByte);
        byte encode = encodeByte[0];

        //读取响应长度
        byte[] responseLengthBytes = new byte[4];
        input.read(responseLengthBytes);
        int responseLength = ByteUtils.byte2Int(responseLengthBytes);

        //读取命令
        byte[] responseBytes = new byte[responseLength];
        input.read(responseBytes);
        String response = "";
        if(Encode.UTF8 == encode){
            response = new String(responseBytes, "UTF-8");
        }else if(Encode.GBK == encode){
            response = new String(responseBytes, "GBK");
        }
        //组装请求返回
        HttpResponse resp = new HttpResponse(encode, response, responseLength);
        return resp;
    }

    // 序列化请求信息
    public static void writeRequest(OutputStream output, HttpRequest request) throws IOException {
        //将response响应返回给客户端
        output.write(request.getEncode());
        //output.write(response.getResponseLength());直接write一个int类型会截取低8位传输丢弃高24位
        output.write(ByteUtils.int2ByteArray(request.getCommandLength()));
        if(Encode.UTF8 == request.getEncode()){
            output.write(request.getCommand().getBytes("UTF-8"));
        }else if(Encode.GBK == request.getEncode()){
            output.write(request.getCommand().getBytes("GBK"));
        }
        output.flush();
    }

    // 序列化响应信息
    public static void writeResponse(OutputStream output, HttpResponse response) throws IOException{
        //将response响应返回给客户端
        output.write(response.getEncode());
        //output.write(response.getResponseLength());直接write一个int类型会截取低8位传输丢弃高24位
        output.write(ByteUtils.int2ByteArray(response.getResponseLength()));
        if(Encode.UTF8 == response.getEncode()){
            output.write(response.getResponse().getBytes("UTF-8"));
        }else if(Encode.GBK == response.getEncode()){
            output.write(response.getResponse().getBytes("GBK"));
        }
        output.flush();
    }
}
