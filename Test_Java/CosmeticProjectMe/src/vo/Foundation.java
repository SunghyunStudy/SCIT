package vo;

public class Foundation extends Product{
    String texture;

    public Foundation() {
    }

    public Foundation(String texture) {
        this.texture = texture;
    }

    public Foundation(String productNo, String name, int price, String texture) {
        super(productNo, name, price);
        this.texture = texture;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }


    @Override
    public String toString() {
        String temp = (texture.equals("1")) ? "크림" : "스틱";
        return super.toString() + String.format("파운데이션 제형 : %s", temp);
    }
}
