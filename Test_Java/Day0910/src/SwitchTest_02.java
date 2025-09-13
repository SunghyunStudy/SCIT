public class SwitchTest_02 {
    public static void main(String[] args) {
        String family = "father";


        // break가 없으면 밑으로 내려감.
        switch (family){
            case "Mother":
                System.out.println("엄마"); break;
            case "father":
                System.out.println("아버지");
            case "Father":
                System.out.println("아빠"); break;
            case "Brother":
                System.out.println("형제"); break;
            case "Sister":
                System.out.println("자매"); break;
            default:
                System.out.println("지나가는 사람"); break;
        }
    }
}
