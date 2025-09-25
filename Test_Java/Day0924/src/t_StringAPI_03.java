public class StringAPI_03 {
    public static void main(String[] args) {
        String str = "123456789";
        char ch = str.charAt(0);    // 전달된 index의 위치에 있는 문자 1개 반환
        System.out.println(ch);

        int i = str.codePointAt(0);
        System.out.println(i);

        System.out.println("Apple".compareTo("apple")); // 0 같, 음:앞작, 양:앞크
        System.out.println("AppLe".compareToIgnoreCase("apple"));

        // contains(CharSequence) // String, StringBuffer, StringBuilder
        /*
        startsWith()
        endsWith()
        indexOf()
        lastIndexOf()
        split()
        isBlank()
        isEmpty()
        length()
        matches()
        trim()
         */

        String song = "I was wondering if after all these years you'd like to meet";
        String song2 = "나리 나리 개나리 입에 따다 물고요!";
        System.out.println(song.contains("like"));  // t
        System.out.println(song.contains(new StringBuffer("meet")));
        System.out.println(song.contains(new StringBuilder("these")));

        System.out.println(song.startsWith("I"));
        System.out.println(song.endsWith("meet!"));

        System.out.println("나리의 위치: " + song2.indexOf("나리"));
        System.out.println("나리의 위치: " + song2.lastIndexOf("나리"));

        String[] ary = song.split(" ");   // 구분자 " " 을 기준으로 분해
        System.out.println("split으로 분해된 단어 개수: " + ary.length);
        System.out.println("2번방 단어: " + ary[2]);

        System.out.println("글자 수: " + song.length());

        System.out.println("Blank 1: " + " ".isBlank());    // true (글자가 있냐?)
        System.out.println("Empty 1: " + " ".isEmpty());    // false (공백은 글자)

        System.out.println("Blank 2: " + "".isBlank());     // true
        System.out.println("Empty 2: " + "".isEmpty());     // true "" 만 true

        // 참고 URL : https://moonong.tistory.com/31
        String tel = "010-1234-5678";
        System.out.println("전화번호 형태: " + tel.matches("^\\d{3}-\\d{3,4}-\\d{4}$"));

        String temp = " I have a dream.";
        System.out.println(temp.length());
        System.out.println(temp.trim().length());
    }
}
