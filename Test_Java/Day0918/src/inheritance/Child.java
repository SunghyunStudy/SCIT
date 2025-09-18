package inheritance;

public class Child extends Parent{ // 부모의 setter/getter, output()이 들어있는거임
    // 부모의 것까지 총 3개씩의 생성자, setter, getter가 있는거임
    private int property;

    public Child() {

    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public void print(){
        System.out.println("자식의 데이터 : " + property);
    }
}
