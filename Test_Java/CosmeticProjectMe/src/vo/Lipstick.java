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



    public Lipstick(String productNo, String name, int price, String type, String color) {
        super(productNo, name, price);
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
    public String toString() {
        String textType = (type.equals("1")) ? "립밤" : (type.equals("2")) ? "립글로스" : "틴트";
        String textColor = (color.equals("1")) ? "빨강" : (color.equals("2")) ? "분홍" : "오렌지";

        return super.toString() + String.format(", 립스틱 타입 : %s, 색상 : %s", textType, textColor);
    }
}
