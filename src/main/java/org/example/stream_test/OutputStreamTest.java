package org.example.stream_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * InputStream OutputStream etc..
 */

public class OutputStreamTest {
    public static void main(String[] args) {

        // System 클래스의 out 이라는 static 필드에는 콘솔창에 출력할 수 있는 PrintStream 객체의 참조값이 들어있음.
        PrintStream ps = System.out;

        // 학습을 위해 PrintStream 객체를 부모 타입 OutputStream 으로 받아오기
        // OutputStream 도 1byte 처리 스트림임.
        OutputStream os = ps;

        try {
            // 출력은 flush() 까지 호출해야 출력이 됨.
            os.write(97);
            os.write(98);
            os.write(99);
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
