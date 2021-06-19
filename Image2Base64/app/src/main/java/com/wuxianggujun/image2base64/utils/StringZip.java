package com.wuxianggujun.image2base64.utils;

/**
 * @作者: 无相孤君
 * @QQ: 3344207732
 * @描述:    
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class StringZip {
    
    /**
     * 字符串的压缩
     *
     * @param base64 待压缩的字符串
     *
     * @return 返回压缩后的字符串
     *
     * @throws IOException
     */
    public static String zipBase64(String base64) throws IOException {
        if (null == base64 || base64.length() <= 0) {
            return base64;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream( out );
        // 将 b.length 个字节写入此输出流
        gzip.write( base64.getBytes() );
        gzip.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString( "ISO-8859-1" );
    }

    /**
     * 字符串的解压
     *
     * @param zipBase64 需要解压的字符串
     *
     * @return 返回解压缩后的字符串
     *
     * @throws IOException
     */
    public static String upZip(String zipBase64) throws IOException {
        if (null == zipBase64 || zipBase64.length() <= 0) {
            return zipBase64;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        ByteArrayInputStream in = new ByteArrayInputStream( zipBase64 .getBytes( "ISO-8859-1" ) );

        // 使用默认缓冲区大小创建新的输入流
        GZIPInputStream gzip = new GZIPInputStream( in );

        byte[] buffer = new byte[256];
        int n = 0;
        while ((n = gzip.read( buffer )) >= 0) {// 将未压缩数据读入字节数组
            // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
            out.write( buffer, 0, n );
        }
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString( "ISO-8859-1" );
    }

    
    
}
