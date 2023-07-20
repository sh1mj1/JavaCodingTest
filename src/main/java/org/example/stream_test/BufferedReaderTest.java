package org.example.stream_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedReaderTest {

    public static void main(String[] args) {
        InputStream is = System.in; // 1 byte 처리 스트림

        InputStreamReader isr = new InputStreamReader(is); // 2byte 처리 스트림

        BufferedReader br = new BufferedReader(isr); // 기능이 더 향상된 Reader

        try {
            System.out.println("문자열 한 줄 입력: ");

            String line = br.readLine(); // 여기서 키보드로 입력을 받음
            System.out.println("입력한 문자열: " + line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
