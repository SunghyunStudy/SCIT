public class OperTest2 {
    public static void main(String[] args) {
        int age = 21;

        // 만 18세 이상 성인이면 true, 아니면 false가 저장되도록 3항연산자로 만들어보시오.
        boolean isAdult = (age >= 18) ? true : false;

        // 입장료가 35000원인 놀이동산에서 할인율을 계산하고 싶다.
        // 만 8세 이하이거나 65세 이상이면 20% 할인을 한다.
        // 9~64는 할인율 없음.
        // 출력은 할인율 (0.2 or 0이 출력)
        // 조건식에는 3항연산자 + 비교연산자
        int fee = 35000;
        age = 66;
        double discountRatio = (age <= 8  ||  age >= 65) ? 0.2 : 0 ;
//        discountRatio = (age > 8  && age < 65) ? 0 : 0.2 ;

        System.out.printf("지불 요금: %,d원입니다.%n", (int)(fee*(1-discountRatio)) );
    }
}
