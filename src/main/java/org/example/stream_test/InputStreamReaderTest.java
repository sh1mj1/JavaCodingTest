package org.example.stream_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamReaderTest {
    public static void main(String[] args) {

        InputStream is = System.in;

        InputStreamReader isr = new InputStreamReader(is);

        try {
            System.out.println("입력: ");
            // 한글의 코드값도 읽을 수 있음.
            int code = isr.read();
            System.out.println("code:" + code);

            // 코드값에 대응되는 문자 얻어내기
            char ch = (char) code;
            System.out.println("char: " + ch);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
