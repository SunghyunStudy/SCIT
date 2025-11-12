public class ExceptionTest_01 {
    public static void main(String[] args) {

        try {
            String[] str = {"abc", "123", "0", "12"};

            System.out.println("배열의 크기: " + str.length);
            String temp = str[str.length - 1];      // ArrayIndexOutOfBoundsException
            //  자바 가상머신이 에러를 객체로 던져줌 그걸 잡아서 예외처리 해주는거임

            // 문자열 형태의 숫자를 연산이 가능한 형태로 변환..
            int i1 = Integer.parseInt(str[2]);
            int i2 = Integer.parseInt(str[1]);
            int i3 = Integer.parseInt(str[0]);      // NumberFormatException

            System.out.println("연산의 결과 : " + (i2 / i1));   // ArithmeticException

            System.out.println("에러가 발생하지 않으면 출력!!");
        } catch (ArrayIndexOutOfBoundsException e){
            // 방법 1  ** 권장 O **
            e.printStackTrace();    // 에러가 발생되진 않지만 진짜 에러처럼 메시지가 발생함 (발생 위치를 알 수 있음)

            // 방법 2     권장 x
//            String msg = e.getMessage();    // 출력 : Index 4 out of bounds for length 4
//            System.out.println(msg);

            // 방법 3     권장 x
            // System.out.println(e.getMessage());
        }catch(ArithmeticException e){
            System.out.println(e.getMessage());   // For input string: "abc"
        }catch(NumberFormatException e){        // 위의ㅡ catch에서 잡히면 얘는 실행이 안됨 따라서 마지막으로 갈 수록 범위가 넓어짐 (마지막엔 Exception)
            System.out.println(e.getMessage());
        }


        // 예외 발생이 의심되는 코드를 try로 잡고 예외처리하면 됨.
        try {
            int a[] = null;
            a[0] = 1;       // 0번방에 참조할게 없기 때문에 NullPointerException이나 ArithmeticException이 터질것임.
        }catch (NullPointerException e){
            System.out.println("호옹이");
        }
    }
}
