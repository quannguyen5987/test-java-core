package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SoftObject {
    public static void main(String[] args) {

        // create list

        List<Student> list = Arrays.asList(new Student("A", 3),
            new Student("B", 1),
            new Student("Q", 36));


        // dung Anonymous inner Class

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.lop - o2.lop;
            }
        });
        for (Student student : list) {
            System.out.println(student.lop);
        }

        // dung Lambda Expression

        Collections.sort(list, (o1, o2) -> o1.mssv.compareTo(o2.mssv));

        // forEach java 8 + lambda

        list.forEach((student) -> System.out.println(student.lop));


    }
}

class Student {
    public String mssv;
    public int lop;

    Student(String mssv, int lop) {
        this.mssv = mssv;
        this.lop = lop;
    }
}
