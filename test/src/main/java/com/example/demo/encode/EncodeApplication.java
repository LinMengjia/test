package com.example.demo.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EncodeApplication {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<String> encodeList = new ArrayList<>();
        encodeList.add("ASCII");
        encodeList.add("ISO-8859-1");
        encodeList.add("GB2312");
        encodeList.add("GBK");
        encodeList.add("GB18030");
        encodeList.add("UTF-16");
        encodeList.add("UTF-8");
        while (true){
            String s = scanner.nextLine();
            System.out.println("输入的字符串为： >>> " + s);
            for(String encode : encodeList){
                System.out.println("经过" + encode + "解码后的结果：>>> " + new String(s.getBytes(encode),"UTF-8"));

            }
        }

    }
}
