import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exam_42 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String id;

        System.out.print("주민번호(예. 111111-1111111) : ");
        id = sc.next();

        if (isValid(id) && isNumeric(id) && codeCheck(id) && leapYearCheck(id)) {
            String[] sary = id.split("");
            String month = sary[2] + sary[3];
            String day = sary[4] + sary[5];

            System.out.printf("당신은 %s년 %s월 %s일생 %s 입니다.\n",
                    birthyear(id), month, day, genderCheck(id));
        } else {
            System.out.println("잘못된 주민번호입니다.");
        }
        sc.close();
    }

    private static boolean codeCheck(String id) {
        int count = 2, sum = 0, verif;
        String[] sary = new String[13];
        int[] iary = new int[13];

        String idClean = id.replace("-", "");

        // 길거나 짧으면 컷.
        if (idClean.length() != 13)
            return false;

        // 입력받은 id를 한글자씩 배열에 저장
        sary = idClean.split("");

        // 숫자만 int로 변환하여 iary에 저장
        for (int i = 0; i < sary.length; i++) {
            iary[i] = Integer.parseInt(sary[i]);
        }

        // 알고리즘 연산 후 sum에 합계 저장.
        for (int j = 0; j < iary.length - 1; j++) {
            if (count == 10)
                count = 2;
            sum += iary[j] * count;
            count++;
        }

        verif = 11 - (sum % 11);
        if (verif >= 10) {
            verif = verif % 10;
        }

        return iary[12] == verif;
    }

    private static String birthyear(String id) {
        String[] sary = new String[14];
        String birthYear;
        sary = id.split("");
        birthYear = sary[0] + sary[1];

        if (sary[7].equals("1") || sary[7].equals("2")) {
            return "19" + birthYear;
        } else if (sary[7].equals("3") || sary[7].equals("4")) {
            return "20" + birthYear;
        }
        return "";
    }

    private static String genderCheck(String id) {
        String[] sary = new String[14];
        String birthYear;
        sary = id.split("");

        if (sary[7].equals("1") || sary[7].equals("3")) {
            return "남자";
        } else if (sary[7].equals("2") || sary[7].equals("4")) {
            return "여자";
        }
        return "";
    }

    private static boolean isValid(String id) {
        if (id.length() != 14)
            return false;

        String[] sary = new String[14];
        sary = id.split("");
        if (!(sary[6].equals("-")))
            return false;

        String genderCode = sary[7];
        if (!(genderCode.equals("1") || genderCode.equals("2") || genderCode.equals("3") || genderCode.equals("4"))) {
            return false;
        }

        return true;
    }

    public static boolean isNumeric(String id) {
        String[] idClean = id.split("-");
        String front, back;
        char c; // 숫자 검증을 위한 변수

        if (idClean.length != 2) {
            return false;
        }

        front = idClean[0];
        back = idClean[1];

        if (front.length() != 6 || back.length() != 7) {
            return false;
        }

        for (int i = 0; i < front.length(); i++) {
            c = front.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        for (int i = 0; i < back.length(); i++) {
            c = back.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean leapYearCheck(String id) {
        String[] sary = id.split("");
        String month = sary[2] + sary[3];
        String day = sary[4] + sary[5];

        if (!(month.equals("02") && day.equals("29"))) {
            return true;
        }

        // 생일이 2/29일인 경우에만 윤년을 판별
        int year = Integer.parseInt(birthyear(id));

        return LocalDate.of(year, 1, 1).isLeapYear();
    }
}
