package lamda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
interface MyFunc{
    void access(String s);
}

@FunctionalInterface
interface YourFunc{
    double method();
}

public class LambdaTest_04 {
    public static void main(String[] args) {
        test( (a) -> System.out.println(a) );             // 타입추정
        test2( (a) -> System.out.println(a + a));
        test3( (a) -> System.out.println(a + a));

        List<String> list = List.of("a", "b", "c");  // arrayList임.
        list.forEach( (a) -> System.out.println(a) );  // test3와 같은 개념.

        test4( () -> 3.14 ); // method()의 전달인자가 없기 때문에 ()로 둠 // 화살표는 중괄호라고 생각하고 3.14 앞에는 return이 있다고 생각하면 됨.

        // String.transform 활용법
        String str = "동해물과 백두산이";
        str.transform( (a) -> a.split(" "));
        List<String> strList = List.of(str.transform( (a) -> a.split(" ")));
        strList.forEach( (a) -> System.out.println(a));


        Function<String, String> ff = (a ) -> a + "!";
        String fst = str.transform(ff);

    }

    public static void test(MyFunc t) {
        t.access("Korea");
    }

    public static void test2(MyFunc t) {
        t.access("Korea");
    }

    public static void test3(Consumer<String> t) {
        t.accept("Japan");
    }

    public static void test4(YourFunc y){
        System.out.println(y.method());
    }
}