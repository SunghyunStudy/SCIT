public class StringBufferAPITest_01 {
    public static void main(String[] args) {
        String song = "나리 나리 개나리 입에 따다 물고요!!";
        StringBuffer buff = new StringBuffer(song);

        System.out.println("song의 길이: " + song.length());
        System.out.println("buff의 길이: " + buff.length());
        System.out.println("buff의 용량: " + buff.capacity());

        // 새로운 객체를 계속 만드는 작업이므로 성능이 나빠진다.
        song.concat(" 병아리떼 뿅뿅뿅").concat(" 봄나들이").concat(" 갑니다.");
        System.out.println(song);

        // 메소드 체이닝
        buff.append(" 병아리떼 뿅뿅뿅").append(" 봄나들이").append(" 갑니다.");
        System.out.println(buff);

        buff.insert(3, "장미 ");
        System.out.println(buff);

        buff.delete( buff.indexOf("개나리"), buff.indexOf("개나리")+4);
        System.out.println(buff);

        // replace 메소드를 이용하여 "병아리"를 "강아지" 로 바꾸시오!
        // 결과: "나리 장미 나리 입에 따다 물고요!! 강아지떼 뿅뿅뿅 봄나들이 갑니다."

        buff.replace(buff.indexOf("병아리"),
                buff.indexOf("병아리") + 3,
                "강아지");
        System.out.println(buff);

        String bufString = buff.toString();
    }
}
