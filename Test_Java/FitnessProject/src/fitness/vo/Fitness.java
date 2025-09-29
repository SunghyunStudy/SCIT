/*
 * 어느 피트니스 클럽에서 회원의 건강상태를 관리하기 위해 생성한 VO
 * Fitness 센터에서 관리하는 한 사람의 회원정보
 */

package fitness.vo;

public class Fitness implements Comparable<Fitness> {
    /* 아이디, 이름, 성별, 키, 몸무게, 표준체중, BMI, BMI 결과 */
    private String id;
    private String name;
    private boolean gender;    // true: 남성, false: 여성
    private double height;     // 키는 cm로
    private double weight;
    private double stdWeight;
    private double bmi;
    private String bmiResult;

    // 생성자
    public Fitness() {}

    public Fitness(String id, String name, boolean gender, double height, double weight) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.weight = weight;

        // 표준
        calcStdWeight();
        calcBMI();
    }
    // getter / setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        calcStdWeight();
        calcBMI();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        calcStdWeight();
        calcBMI();
    }

    // 계산식이 있는 일반메소드
    // 표준체중 구하기
    // 남성표준체중 = 키(m) * 키 * 22;   여성표준체중 = 키(m) * 키 * 21;
    private void calcStdWeight() {
        double temp = height / 100;
        stdWeight = (gender) ?  temp * temp * 22 : temp * temp * 21;
    }

    // BMI 구하기 = 몸무게(kg) / 키(m)의 제곱
    private void calcBMI() {
        double temp = height / 100;
        bmi = weight / (temp * temp);
        calcBMIResult();
    }

    // BMI 판단 결과
    private void calcBMIResult() {
        if     (bmi < 18.5) bmiResult = "저체중";
        else if(bmi < 23)   bmiResult = "정상체중";
        else if(bmi < 25)   bmiResult = "과체중";
        else if(bmi < 30)   bmiResult = "경도비만";
        else if(bmi < 35)   bmiResult = "중도비만";
        else                bmiResult = "고도비만";
    }

    @Override
    public String toString() {
        String temp;
        temp = String.format("%3s %s %s %6.2fcm %6.2fkg / 표준체중: %6.2fkg, BMI: %.2f, 결과: %s",
                id, name, (gender)? "남성" : "여성", height, weight,
                stdWeight, bmi, bmiResult);

        return temp;
    }

    // 비교를 위해 사용 (ID 기준)
    @Override
    public int compareTo(Fitness o) {
        int result = (id.compareTo(o.getId()) > 0 ) ? 1:  (id.compareTo(o.getId()) < 1) ? -1 :  0;

        return result;
    }
}
