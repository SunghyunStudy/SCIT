package second;

public class One {
    private int oneData;


    public One(){

    }

    // 3. 아래는 오버로드 생성자임. 기본생성자가 없음 (자식클래스에 오류 발생할거임. 이를 방지하기 위해 기본생성자는 꼭 만들어야함.
    public One(int OneData){
        this.oneData = oneData;
    }

    public int getOneData() {
        return oneData;
    }

    public void setOneData(int oneData) {
        this.oneData = oneData;
    }

    public void output(){
        System.out.println("one data : " + oneData);
    }
}
