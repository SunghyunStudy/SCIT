package myabstract.car;

public class Car extends Vehicle{
    private int displacement;   //배기량
    private int taxMoney;       // 계산된 세금

    public Car(){

    }

    public Car(int displacement, int taxMoney) {
        this.displacement = displacement;
        this.taxMoney = taxMoney;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(int taxMoney) {
        this.taxMoney = taxMoney;
    }

    @Override
    // 상속 받은 추상 메소드를 구현 vehicle의 tax 메서드를 가져온거임.
    public int tax() {
        if(displacement < 1500) taxMoney = (int) (getPrice() * 0.01); // 부모로부터 상속받은 객체에 getter로 접근하기위해 super.를 붙여도 됨
        else if(displacement < 3000) taxMoney = (int) (getPrice() * 0.03);
        else taxMoney = (int) (getPrice() * 0.05);

        return taxMoney;
    }
}
