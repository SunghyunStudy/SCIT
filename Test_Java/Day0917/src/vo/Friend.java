/*
Friend 클래스 안에

멤버변수
기본생성자
오버로딩 생성자
setter, getter
output() 생성


FriendTest 클래스를 만들어서 테스트 하시오.

 */

package vo;

import java.time.LocalDate;

public class Friend {
    private String name;
    private String sns;
    private LocalDate birthday;
    private int age;
    private boolean type;   // 외향 : true , 내향 : false

    public Friend(){

    }

    public Friend(String name, String sns, LocalDate birthday, int age, boolean type) {
        this.name = name;
        this.sns = sns;
        this.birthday = birthday;
        this.age = age;
        this.type = type;
    }

    // private 이기에 setter, getter가 필요함.
    public void setType(boolean type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSns() {
        return sns;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public boolean isType() {
        return type;
    }

    public void output(){
        System.out.printf("이름 : %s,  sns : %s,  생일 : %s,  나이 : %d,  성격 : %s",
                name, sns, birthday, age, (type == true) ? "외향적" : "내향적");
    }

}
