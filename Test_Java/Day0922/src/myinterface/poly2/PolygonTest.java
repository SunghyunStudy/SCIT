package myinterface.poly2;

public class PolygonTest {
    public static void main(String[] args) {
        Polygon polygon = null;

        polygon = new Triangle();     // 업캐스팅(묵시적)
//        Polygon p = new Polygon();
//        Triangle t = (Triangle) p;    // 다운캐스팅


        polygon.edgeCount = 3;      // 변의 개수 출력하는데 왜 이 생각을 못했지? 바보같이 polygon가서 코드 만지고 있노..
        polygon.print();            // 변의 개수 : 3개

        polygon = new Rectangle();
        polygon.edgeCount = 4;
        polygon.print();            // 변의 개수 : 4개


    }
}
