package org.example.stream_test;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream OutputStream etc..
 */

public class InputStreamTest {
    public static void main(String[] args) {

        InputStream is = System.in;
        System.out.print("입력: ");

        try{
            int code = is.read(); // 입력한 키의 코드값 알아오기
            System.out.println(code);

            char ch = (char) code; // 코드값에 대응하는 문자 얻어내기
            System.out.println("char: " + ch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main method ended");

    }
}
