package inheritance;

public class Parent {
    private int money;

    // 생성자
    public Parent() {}


    // setter, getter
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public void output(){
        System.out.println("부모의 데이터 : " + money);
    }
}
