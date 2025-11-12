package vo;

public class Foundation extends Product{
    private String texture;

    // 기본 생성자
    public Foundation() {}

    // 고유속성만 받는 생성자
    public Foundation(String texture) {
       this.texture = texture;
    }

    // 공통속성 + 고유속성
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
        String texture =  (this.texture.equals("1")) ? "크림" : "스틱";

        String tmp = super.toString() + String.format("제형=%s", texture);
        return tmp;
    }
}
