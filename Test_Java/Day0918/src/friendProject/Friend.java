package friendProject;

public class Friend {
    private String name;
    private int age;
    private boolean type;   // 외향 : true , 내향 : false

    // 생성자 생성
    public Friend(){

    }

    public Friend(String name, int age, boolean type) {
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isType() {
        return type;
    }

    public void output(){
        System.out.printf("이름 : %s, 나이 : %d,  성격 : %s%n",
                name, age, (type == true) ? "외향적" : "내향적");
    }
}
