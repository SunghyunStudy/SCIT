package abstractPack;

abstract class Polygon{
    int x;
    int y;
    abstract double area();
    void output() {
        System.out.printf("x= %d, y= %d%n", x, y);
    }
}
class MyCircle extends Polygon{
    double radius;

    // 이거 구현 안하면 빨간 밑줄 겁내 뜸.
    @Override
    double area() { // abstract 메서드는 반드시 구현을 해야됨. 대신 output은 구현 안해도 됨(abstract이 아니니까)
        return radius * radius * Math.PI;
    }

    @Override
    void output(){
        super.output();
        System.out.printf("radius = %f, area = %f%n", radius, this.area());
    }
}

class MyRectangle extends Polygon{
    int width;
    int height;

    @Override
    double area() {
        return width * height;
    }

    @Override
    void output() {
        super.output();
        System.out.printf("width = %d, height = %d, area = %f%n ",width, height, this.area());
    }
}

public class PolygonTest {
    public static void main(String[] args) {
        MyCircle c = new MyCircle();
        c.x = 1;        // private이 아니라서 직접접근 가능 (set 안해도 됨)  // x는 Polygon 것인데 private이 아니라서 c로 접근가능.
        c.y = 5;
        c.radius = 7;
        c.output();

        MyRectangle r = new MyRectangle();
        r.x = 5;
        r.y = 10;
        r.width = 4;
        r.height = 3;
        r.output();
    }
}
