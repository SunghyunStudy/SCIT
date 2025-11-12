public class StringAPI_03 {
    public static void main(String[] args) {
        String str = "abcd efg hijk lmn";
        char ch = str.charAt(0);  // 전달된 index의 위치에 있는 문자 1개 반환
        System.out.println(ch);

        int i = str.codePointAt(0); // 전달된 index 위치의 숫자를 아스키코드로
        System.out.println(i);


        System.out.println("Aorea".compareTo("aorea"));   // -32      // 달라지는게 나올때까지 비교해서 앞에꺼에서 뒤에꺼를 뺀 수를 반환
        System.out.println("Korea".compareTo("Korea"));   // 0이면 같다는 의미  음수 : 앞이 작음 / 양수 : 앞이 큼
        System.out.println("Korea".compareToIgnoreCase("korea"));   // 대소문자 구분 x


        // contains(CharSequence) // String, StringBuffer, StringBuilder
        String song = "I was wondering if after all these years you'd like to meet";
        String song2 = "나린 나리 개나리 입에 따다 물고요!";

        System.out.println(song.contains("like"));  // true
        System.out.println(song.contains(new StringBuffer("meet")));        // 쓰레드 세이프 있음  // true
        System.out.println(song.contains(new StringBuilder("these")));       // 쓰레드 세이프 없음  // true

        System.out.println("startswith: " + song.startsWith("I"));
        System.out.println("endswith: " + song.endsWith("et"));
        System.out.println("나리의 위치 : " + song2.indexOf("나리"));   // 나리의 위치 없으면 -1 반환
        System.out.println("나리의 위치 : " + song.lastIndexOf("나리"));
        System.out.println("문장의 마지막 위치 : " + song.lastIndexOf("meet"));

        String[] ary = song.split(" ");

        System.out.println("split으로 분해된 단어 개수 : " + ary.length);
        System.out.println("2번방 단어 : " + ary[2]);

        System.out.println("글자 수 : " + song.length());

        System.out.println("Blank 1 : " + " ".isBlank());  // true (글자가 있냐?)
        System.out.println("Empty 1 : " + " ".isEmpty());  // false (공백도 포함)

        System.out.println("Blank 2 : " + "".isBlank());
        System.out.println("Empty 2 : " + "".isEmpty());

        // 참고 URL : https://moonong.tistory.com/31
        String tel = "010-1234-5678";
        System.out.println("전화번호 형태 : " + tel.matches("^\\d{3}-\\d{3,4}-\\d{4}$"));  // 정규표현식

        String tmp = " I have a dream.";
        System.out.println(tmp.length());
        System.out.println(tmp.trim().length()); // 앞 뒤 공백을 제거

    }
}
