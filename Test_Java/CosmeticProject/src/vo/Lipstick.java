package vo;

public class Lipstick extends Product {
    String type;
    String color;

    public Lipstick() {
    }

    public Lipstick(String type, String color) {
        this.type = type;
        this.color = color;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String output() {
        return super.output();
    }
}
