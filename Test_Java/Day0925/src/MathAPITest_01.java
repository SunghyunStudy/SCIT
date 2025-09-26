public class MathAPITest_01 {
    public static void main(String[] args) {
        double radius = 5.0;
        double area = 0;
        area = Math.PI * (radius * radius);
        System.out.println("반지름이 5인 원의 넓이) : " + area);

        // math
        // 1) max, min, floor, ceil, round, random
        double first = 15.24568;
        double second = 4.547123957;
        System.out.println("max = " + Math.max(first, second));
        System.out.println("min = " + Math.min(first, second));

        System.out.println();
        System.out.println("<반올림>");
        System.out.println("ceil(올림) = " + Math.ceil(first));
        System.out.println("floor(내림) = " + Math.floor(first));
        System.out.println("round(반올림) = " + Math.round(second));

        System.out.println();
        System.out.println("<음수 반올림>");
        double third = -15.24568;
        double fourth = -4.547123957;
        System.out.println("ceil(올림) = " + Math.ceil(third));
        System.out.println("floor(내림) = " + Math.floor(third));
        System.out.println("round(반올림) = " + Math.round(fourth));

        // 난수발생(random())
        System.out.println();
        System.out.println("<난수발생>");
        double rand;
        for(int i = 0; i < 10; ++i){
            rand = Math.random() * 100;  // (int)는 내림임.  // 0이상 100미만
            System.out.println((int)rand + ", " + rand);
        }



    }
}
