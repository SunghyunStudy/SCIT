package second;

public class Two extends One{
    private int twoData;


    // 3-1 jvm이 알아서 기본 생성자를 만들어줌
    public Two(){
        // super(); : 부모의 기본생성자를 호출.
    }

    public Two(int twoData){
        // super(); 이건 자동으로 호출이 됨 그렇기 때문에 부모 클래스에 기본생성자가 없다면 오류가 남. ******** 중요 **********
        this.twoData = twoData;
    }

    // 오버로드 생성자
    public Two(int oneData, int twoData){
        super(oneData);     // 부모의 오버로드 생성자를 호출
        // this(twoData); 이거는 못씀 this()도 super()도 맨 위에 와야되는 애들이기 때문에.
        // this(twoData);는 같은 클래스 내에 있는 다른 생성자를 호출하는 코드입니다.
        // Two 클래스 안에서 twoData라는 정수형 매개변수 하나를 받는 다른 생성자, 즉 public Two(int twoData)를 호출하겠다는 의미입니다
        this.twoData = twoData;
    }



    public int getTwoData() {
        return twoData;
    }

    public void setTwoData(int twoData) {
        this.twoData = twoData;
    }


    @Override
    public void output() {

        System.out.println("two data : " + twoData);
        super.output();    // super.output() : 부모의 output()을 호출한다.
    }
}
