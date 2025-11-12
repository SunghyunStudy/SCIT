package pet.vo;

public class Bird extends Pet {
    private String species; // 종

    // 생성자 : Code Here
    public Bird(){};

    public Bird(String id, String name, int age, String species) {
        super(id, name, age);
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("새종류: %s ", species);
    }
}
