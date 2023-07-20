package org.example.javadoit.comparable_and_comparator;


import java.util.Arrays;
import java.util.Comparator;

class Student implements Comparable<Student> {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
//        if(this.age > o.age) return 1;
//        else if (this.age < o.age) return  -1;
//        return 0;
        return this.age - o.age;

    }
}

public class ComparableTest {
    public static void main(String[] args) {
        Student[] array = new Student[5];
        array[0] = new Student("AA", 22);
        array[1] = new Student("BB", 23);
        array[2] = new Student("CC", 19);
        array[3] = new Student("DD", 20);
        array[4] = new Student("EE", 23);

        Arrays.sort(array);
        for (Student st : array) {
            System.out.println("name: " + st.name + ", age: " + st.age);
        }

        String[] strArray = {"Apple", "Banana", "Tangerine", "Pear", "Blueberry"};
//        Arrays.sort(strArray, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                if (o1.length() > o2.length()) return -1;
//                if (o1.length() < o2.length()) return 1;
//                return o1.compareTo(o2); // 문자열 길이가 같을 경우 알파벳 기준으로 오름차순 정렬.
//            }
//        });

        Arrays.sort(strArray, (o1, o2) -> {
            if (o1.length() > o2.length()) return -1;
            else if (o1.length() < o2.length()) return 1;
            return o1.compareTo(o2);
        });


        for (String str : strArray) {
            System.out.println(str);
        }

    }
}

