package vo;

public abstract class Fitness implements Comparable<Fitness> {
    // --- 필드 ---
    private String id;
    private String name;
    private double height;
    private double weight;
    // trainerName 필드: 값이 있으면 VIP, null이면 일반 회원
    private String trainerName;

    // --- 계산 필드 ---
    protected double stdWeight; // 자식 클래스가 접근할 수 있도록 protected로 설정
    private double bmi;
    private String bmiResult;

    // --- 생성자 ---
    public Fitness(String id, String name, double height, double weight, String trainerName) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.trainerName = trainerName; // null 또는 트레이너 이름이 저장됨
        calculateAll(); // 객체 생성 시 모든 수치 자동 계산
    }

    // --- 추상 메소드 ---
    // 표준 체중 계산법은 자식 클래스(MaleMember, FemaleMember)에서 반드시 구현해야 함
    public abstract void calcStdWeight();

    // --- 공통 메소드 ---
    // BMI 계산
    private void calcBMI() {
        double heightInMeters = height / 100.0;
        this.bmi = this.weight / (heightInMeters * heightInMeters);
        calcBMIResult();
    }

    // BMI 결과 판정
    private void calcBMIResult() {
        if (bmi < 18.5) bmiResult = "저체중";
        else if (bmi < 23) bmiResult = "정상체중";
        else if (bmi < 25) bmiResult = "과체중";
        else if (bmi < 30) bmiResult = "경도비만";
        else if (bmi < 35) bmiResult = "중도비만";
        else bmiResult = "고도비만";
    }

    // 모든 계산을 한 번에 실행
    protected void calculateAll() {
        calcStdWeight(); // 자식 클래스에서 구현된 메소드가 호출됨 (다형성)
        calcBMI();
    }

    // --- Getter ---
    public String getId() { return id; }
    public String getName() { return name; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public String getTrainerName() { return trainerName; }
    // trainerName의 null 여부로 VIP인지 판별
    public boolean isVip() { return trainerName != null; }

    // --- Setter ---
    public void setHeight(double height) {
        this.height = height;
        calculateAll();
    }

    public void setWeight(double weight) {
        this.weight = weight;
        calculateAll();
    }

    // ID 기준 정렬
    @Override
    public int compareTo(Fitness other) {
        return this.id.compareTo(other.getId());
    }

    @Override
    public String toString() {
        // 기본 출력 정보 (회원 유형과 성별은 자식 클래스에서 추가)
        return String.format("%s\t%s\t%.2fcm\t%.2fkg\t(표준: %.2fkg, BMI: %.2f - %s)",
                id, name, height, weight, stdWeight, bmi, bmiResult);
    }
}