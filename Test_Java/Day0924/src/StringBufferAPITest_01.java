import java.lang.StringBuffer;

public class StringBufferAPITest_01 {
    public static void main(String[] args) {
        String song = "나리 나리 개나리 입에 따다 물고요!!";
        StringBuffer buff = new StringBuffer(song);

        System.out.println("song의 길이: " + song.length());
        System.out.println("buff의 길이: " + buff.length());
        System.out.println("buff의 용량: " + buff.capacity());

        // 새로운 객체를 계속 만드는 작업이므로 성능이 나빠진다.
        song.concat(" 병아리떼 뿅뿅뿅").concat(" 봄나들이").concat(" 갑니다 ");
        // concat도 반환하는 변수를 안만들어도 되는데
        // String은 원본이 바뀌지 않음 (Immutable)
        // concat을 할 때 마다 원본이 바뀌는게 아니라 객체가 새로 생겼다가 없어지는거임
        System.out.println(song);


        buff.append(" 병아리떼 뿅뿅뿅").append(" 봄나들이").append(" 갑니다 "); // 반환이 아니라 buff에 바로 붙음 (= 자기자신을 반환)
        // 원본이 계속 바뀌고 있는거임. 한번 생성된 객체에 계속 꼬리에꼬리를 물고 반환되고 있음 = Method chaining
        // StringBuffer는 원본을 바꿀 수 있음.
        System.out.println(buff);


        buff.insert(3, "장미 ");
        System.out.println(buff);

        buff.delete(buff.indexOf("개나리"), buff.indexOf("개나리") + 5);        // 개나리의 인덱스 위치에서 3개를 지워라
        System.out.println(buff);

        // replace 메소드를 이용하여 "병아리"를 "강아지"로 바꾸시오!
        // 결과 : "나리 장미 나리 입에 따다 물고요!! 강아지떼 뿅뿅뿅 봄나들이 갑니다."

        buff.replace(buff.indexOf("병아리"), buff.indexOf("병아리") + 3, "강아지");
        System.out.println(buff);

        String bufString = buff.toString();


    }
}
