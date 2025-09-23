package myabstract.car;

public class CarTest {
    public static void main(String[] args) {
        Car car = null;
        car = new Car();

        car.setModelName("그랜저");
        car.setPrice(40000000);
        car.setDisplacement(3500);

        output(car);

        car.setModelName("K5");
        car.setPrice(30000000);
        car.setDisplacement(2000);

        output(car);
    }

    public static void output(Car car){
        System.out.printf("모델명 : %s  가격 : %,d  배기량 : %,d   세금 : %,d%n",
                car.getModelName(), car.getPrice(), car.getDisplacement(), car.tax());      // car.tax()인게 중요
    }
}
