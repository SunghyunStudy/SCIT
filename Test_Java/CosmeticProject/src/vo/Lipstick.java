package vo;

public class Lipstick extends Product {
    String type;
    String color;

    // 기본 생성자 필수로 생성해야됨
    public Lipstick() {
    }

    // 자기 자신 즉, 고유속성만 받는 생성자
    public Lipstick(String type, String color) {
        this.type = type;
        this.color = color;
    }

    // 추가함
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


    // 자신의 멤버변수를 문자열로 연결하여 반환하는 메소드임.
    // 오버라이딩을 하지 않으면 Object로부터 상속받은 raw 형태의 문자열인 hash 코드가 반환됨
    @Override
    public String toString() {
        String type = (this.type.equals("1")) ? "립밤" :
                this.type.equals("2") ? "립글로스" : "틴트";  // 멤버변수를 가리킬때는 this가 있어야됨.
        // 타입에 숫자만 들어있기 때문에 이 과정이 필요
        String color = (this.type.equals("1")) ? "빨강" :
                this.type.equals("2") ? "분홍" : "오렌지";


        // 아래까지만 입력하니까 product만 출력됨
        String tmp = super.toString() + String.format("타입 : %s, 색상 : %s%n", type, color);
        return tmp;
    }
}
