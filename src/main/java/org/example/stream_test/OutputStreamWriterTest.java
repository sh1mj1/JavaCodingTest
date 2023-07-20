package org.example.stream_test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class OutputStreamWriterTest {
    public static void main(String[] args) {

        // System 클래스의 out 이라는 static 필드 -> PrintStream 객체의 참조값을 가짐.
        PrintStream ps = System.out;

        // PrintStream 객체를 부모 타입 OutputStream 으로 받아오기.
        // OutputStream : 1byte 처리 스트림
        OutputStream os = ps;

        // 2byte 처리 스트림
        OutputStreamWriter osw = new OutputStreamWriter(os);

        try {
            osw.write(44032);
            osw.write("\n");
            osw.write("한글 입력함");
            osw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
