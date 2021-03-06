package com.nju.protocol.http;

// 字节转化工具类
public final class ByteUtils {
    // 将byte数组转化为int数字
    public static int byte2Int(byte[] bytes){
        int num = bytes[3] & 0xFF;
        num |= ((bytes[2] << 8) & 0xFF00);
        num |= ((bytes[1] << 16) & 0xFF0000);
        num |= ((bytes[0] << 24) & 0xFF000000);
        return num;
    }

    // 将int类型数字转化为byte数组
    public static byte[] int2ByteArray(int i){
        byte[] result = new byte[4];
        result[0]  = (byte)(( i >> 24 ) & 0xFF);
        result[1]  = (byte)(( i >> 16 ) & 0xFF);
        result[2]  = (byte)(( i >> 8 ) & 0xFF);
        result[3]  = (byte)(i & 0xFF);
        return result;
    }
}
