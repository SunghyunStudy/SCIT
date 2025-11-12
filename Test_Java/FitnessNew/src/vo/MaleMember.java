package vo;

public class MaleMember extends Fitness {

    // 생성자
    public MaleMember(String id, String name, double height, double weight, String trainerName) {
        super(id, name, height, weight, trainerName);
    }

    /**
     * 추상 메소드 구현: 남성의 표준 체중 계산
     * 표준체중 = 키(m) * 키(m) * 22
     */
    @Override
    public void calcStdWeight() {
        double heightInMeters = getHeight() / 100.0;
        super.stdWeight = heightInMeters * heightInMeters * 22;
    }

    @Override
    public String toString() {
        String memberType = isVip() ? "[VIP 회원]" : "[일반 회원]";
        String genderStr = "[남성]";

        // 부모의 정보에 회원 유형, 성별, 트레이너 정보를 추가하여 최종 출력
        String finalOutput = String.format("%-9s %-4s %s", memberType, genderStr, super.toString());
        if (isVip()) {
            finalOutput += String.format(" (담당: %s)", getTrainerName());
        }
        return finalOutput;
    }
}