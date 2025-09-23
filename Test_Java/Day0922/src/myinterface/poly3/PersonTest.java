package myinterface.poly3;

import myinterface.poly2.Polygon;

class Person{
    String name;
    int age;

    public Person(){}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Junior extends Person{

}

class Senior extends Person{

}


public class PersonTest {
    public static void main(String[] args) {

        int[] iary = new int[10];
        int[] iarr = {1, 2, 3, 4, 5};

        Person[] pary = new Person[10]; // 객체배열
        pary[0] = new Person("전우치", 21);
        Person p = new Person("홍길동", 22);

        Person[] parr = {new Person("손오공", 33), new Person("저팔계", 24)};

    }

    Person[] per = {
            new Junior(), new Senior()          // 자식 불러오기 가능한지 확인
    };
}
