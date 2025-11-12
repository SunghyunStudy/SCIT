/*
 * 동물병원에서 동물을 관리하기 위한 VO 클래스
 */


package pet.vo;

public abstract class Pet implements Comparable<Pet> {
    private String id;
    private String name;
    private int age;

    // 생성자
    public Pet() {

    }

    public Pet(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%3s %s %d살 ", id, name, age);
    }


    // 비교를 위해 사용 (ID 기준)
    @Override
    public int compareTo(Pet pet) {
        return id.compareTo(pet.getId());
    }

}

