public class ArrayTest_02 {
    public static void main(String[] args) {
        // 배열을 생성하면 내부 속성으로 length라는 RO(read only) 속성이 생김.
        int[] iary = new int[] {1, 2, 4, 45, 7, 8, 2, 3, 10, 4, 43, 31};

        System.out.println("iary는 방이 몇 개? " + iary.length);
        System.out.println("마지막 방번호? " + (iary.length - 1));

        for(int i = 0; i < iary.length; ++i)
            System.out.println(iary[i]);
    }
}
