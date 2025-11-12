package Exam_35to37;

public class CarDriver {
    public static void main(String[] args) {

        Car c = new Car();


        c.setModelName("그렌저");
        c.setPrice(40000000);
        c.setDisplacement(3500);

        print(c);

    }

    public static void print(Car c){
        String result = String.format("모델명 : %s    가격 : %,d원    배기량 : %,dcc    세금 : %,d원%n", c.getModelName(), c.getPrice(), c.getDisplacement(), c.tax());
        System.out.printf(result);
    }
}
