package Exam_31;

public class PetVo {
    String name;
    String type;
    int age;

    public PetVo(){}
    public PetVo(String name, String type, int age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getType(){return type;}
    public void setType(String type){this.type = type;}

    public int getAge(){return age;}
    public void setAge(int age){this.age = age;}

    @Override
    public String toString() {
        String result = String.format("이름 : %s  나이 : %d  종 : %s%n", name, age, type);
        return result;
    }
}
