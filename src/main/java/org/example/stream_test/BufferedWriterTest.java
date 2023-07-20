package org.example.stream_test;

import java.io.*;

public class BufferedWriterTest {
    public static void main(String[] args) {
        // System 클래스에 out 이라는 static 필드.
        // 콘솔 창에 출력할 수 있는 PrintStream 객체 참조값이 들어있음
        PrintStream ps = System.out;

        OutputStream os = ps; // 1 byte 처리 스트림
        OutputStreamWriter osw = new OutputStreamWriter(os); // 2 byte 처리 스트림

        BufferedWriter bw = new BufferedWriter(osw);

        try {
            bw.write("하나 \n");
            bw.write("둘");
            bw.newLine(); // 개행 기호를 출력해주는 메소드가 있음.
            bw.write("셋");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
