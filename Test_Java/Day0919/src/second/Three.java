package second;

public class Three extends Two{
    private int threeData;

    public Three(){
        super();        // super(); : 부모의 기본생성자를 호출.
    }


    public int getThreeData() {
        return threeData;
    }

    public void setThreeData(int threeData) {
        this.threeData = threeData;
    }

    @Override
    public void output() {
        System.out.println("three data : " + threeData);

        super.output();     // two의 output임.
    }
}
