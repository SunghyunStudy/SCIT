/*
    call by reference :

    8개의 기본 자료형은 stack에 변수가 생기고 값이 들어가는데
    레퍼런스는 주소값을 가리키게 됨

    다른 메소드에서 생성된 변수에 주소값이 들어가기 때문에 바뀌게 되면 값이 완전 바뀜.
 */

public class MethodCall_02 {
    public static void main(String[] args) {
        int [] ary = {10, 20};

        System.out.println("[0] = " + ary[0] + ", [1] = " + ary[1]);
        exchange(ary);
        System.out.println("[0] = " + ary[0] + ", [1] = " + ary[1]);
    }

    public static void exchange(int[] ary){
        int temp;

        temp = ary[0];
        ary[0] = ary[1];
        ary[1] = temp;
    }
}