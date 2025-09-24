package vo;

public class Foundation extends Product{
    String texture;

    // 기본 생성자
    public Foundation() {
    }

    // 고유속성만 받는 생성자
    public Foundation(String texture) {
        this.texture = texture;
    }

    // 공통 속성 + 고유속성 -> 이걸 만들지 않으려면 inputProduct에서 setter를 또 써야됨 귀찮아짐 방법은 나중에 찾아보자.
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
        String texture = (this.texture.equals("1")) ? "크림" : "스틱";

        String tmp = super.toString() + String.format("제형 : %s%n", texture);
        return tmp;
    }
}
