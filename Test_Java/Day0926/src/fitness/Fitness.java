/**
 * 어느 피트니스 클럽에서 회원의 건강상태를 관리하기 위해 생성한 VO
 */


package fitness;

public class Fitness {
    /*
    아이디, 이름, 성별, 키, 몸무게, 표준체중, BMI, BMI 결과
    계산해야되는거는 getter만 만들면 됨. getter도 필요없긴 함?
    boolean 타입은 getter가 getGender가 아니라 isGender임.
     */
    private String id;
    private String name;
    private boolean gender;         // 남성 : true, 여성 : false
    private double height;
    private double weight;
    private double stdWeight;
    private double bmi;
    private String bmiResult;

    public Fitness(){}

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


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setHeight(double height) {
        this.height = height;
        // 키가 바뀌는 경우
        // set을 해서 키가 바뀌면 자동으로 표준체중과 BMI를 계산할 수 있도록 ....
        calcStdWeight();
        calcBMI();
    }

    public void setWeight(double weight) {
        this.weight = weight;
        calcStdWeight();
        calcBMI();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getStdWeight() {
        return stdWeight;
    }

    public double getBmi() {
        return bmi;
    }

    public String getBmiResult() {
        return bmiResult;
    }

    // 계산식이 있는 일반메소드 (private으로 하는 이유는 setting이 불가능 해야됨. 항상 일정한 결과가 나와야됨)
    // 표준체중 구하기
    // 남자 표준 체중 = 키(M) * 키(M) * 22
    // 여자 표준 체중 = 키(M) * 키(M) * 21
    private void calcStdWeight(){
        double temp = height / 100;
        stdWeight = (gender) ? temp * temp * 22 : temp * temp * 21;

    }

    // BMI 구하기
    // 몸무게(kg) / 키(m)의 제곱
    private void calcBMI(){
        double temp = height / 100;
        bmi = weight / (temp * temp);
        calcBmiResult();
    }

    // BMI 결과 구하기
    private void calcBmiResult(){
        if(bmi < 18.5) bmiResult = "저체중" ;
        else if (bmi >= 18.5 && bmi < 23) bmiResult = "정상";
        else if (bmi >= 23 && bmi < 25) bmiResult = "과체중";
        else if (bmi >= 25 && bmi < 30) bmiResult = "1단계 비만";
        else if (bmi >= 30 && bmi < 35) bmiResult = "2단계 비만";
        else                            bmiResult = "3단계 비만";
    }

    @Override
    public String toString(){
        String temp;
        temp = String.format("%3s, %s, %s, %6.2fcm, %6.2fkg / 표준체중 : %.2fkg,  BMI : %.2f,  결과 : %s"
                , id, name, (gender) ? "남성" : "여성", height, weight, stdWeight, bmi, bmiResult);
        return temp;
    }
}
