package pet.vo;

public class Mammal extends Pet {
    String furColor;	// 털 색
    String breed;		// 종류

    // 생성자 Code Here

    public Mammal() {
    }

    public Mammal(String id, String name, int age, String furColor, String breed) {
        super(id, name, age);
        this.furColor = furColor;
        this.breed = breed;
    }


    // getter, setter

    public String getFurColor() {
        return furColor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("털색: %s, 종류: %s ", furColor, breed);
    }
}
