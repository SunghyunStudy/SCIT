package first;

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


    // private -> (default) -> protected -> public 오른쪽으로 갈 수록 범위가 넓어지고 접근 지정자는 범위가 더 넓어야함.
    @Override   // 어노테이션 : 컴파일러에 오버라이드 잘 했는지 알려달라는 의미
    public void output(){
        System.out.println("자식의 데이터 : " + property);
    }

//    public void output(int a){  // 오버라이딩을 한 것을 오버로드 한거임.
//        System.out.println("자식의 데이터 : " + property);
//    }
}
