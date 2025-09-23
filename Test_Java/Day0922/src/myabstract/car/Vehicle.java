package myabstract.car;

abstract class Vehicle {
    private String modelName;
    private int price;


    public Vehicle(){

    }

    public Vehicle(String modelName, int price) {
        this.modelName = modelName;
        this.price = price;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // 추상메소드
    public abstract int tax();
}
