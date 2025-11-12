package Exam_35to37;

public class Car extends Vehicle{
    private int displacement;
    private int taxMoney;

    public Car(){}

    public Car(int displacement, int taxMoney){
        this.displacement = displacement;
        this.taxMoney = taxMoney;
    }

    public int getDisplacement(){
        return displacement;
    }
    public void setDisplacement(int displacement){
        this.displacement = displacement;
    }

    public int getTaxMoney(){
        return taxMoney;
    }
    public void setTaxMoney(int taxMoney){
        this.taxMoney = taxMoney;
    }

    @Override
    public int tax(){
        taxMoney = (displacement >= 3000) ?
                (int)(super.getPrice() * 0.05) : (displacement < 3000 && displacement >= 1500) ?
                (int)(super.getPrice() * 0.03) : (int)(super.getPrice() * 0.01);
        return taxMoney;
    }
}
