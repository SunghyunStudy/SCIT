package myinterface.poly2;

public abstract class Polygon {
    int edgeCount;  // 다각형의 변의 개수
    abstract double area();     // 면적을 구하는 메소드

    void print(){
        System.out.println("변의 개수 : " + this.edgeCount);
    }
}

class Triangle extends Polygon{

    @Override
    double area() {
        return 0;
    }
}


class Rectangle extends Polygon{

    @Override
    double area() {
        return 0;
    }
}
