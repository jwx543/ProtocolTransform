package com.nju.jwx.http;

// 协议请求定义
public class HttpRequest {
    // 协议编码
    private byte encode;
    // 命令
    private String command;
    // 命令长度
    private int commandLength;

    public HttpRequest(){
        super();
    }

    public HttpRequest(byte encode, String command, int commandLength) {
        super();
        this.encode = encode;
        this.command = command;
        this.commandLength = commandLength;
    }

    public byte getEncode() {
        return encode;
    }

    public void setEncode(byte encode) {
        this.encode = encode;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getCommandLength() {
        return commandLength;
    }

    public void setCommandLength(int commandLength) {
        this.commandLength = commandLength;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "encode=" + encode +
                ", command='" + command + '\'' +
                ", commandLength=" + commandLength +
                '}';
    }
}
