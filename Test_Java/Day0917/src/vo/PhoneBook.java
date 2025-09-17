package vo;

public class PhoneBook {
    // 이름(S), 전화번호(S), 관계(S), 메모(S), address(S)

    private String name;
    private String phone;
    private String relation;
    private String address;
    private String memo;

    // 기본생성자 PhoneBookTest에서 setting을 하지 않고 출력을 해도 출력될 수 있도록...
    // 기본생성자는 return 타입이 있으면 안됨 (void 같은거)
    public PhoneBook() {
    }

    // 생성자 오버로딩(매개변수 갯수나 타입을 다르게하는거)
    public PhoneBook(String name, String phone) {
        // ***** this() 는 생성자 내 최상단에 위치해야 하며
        // 다른 문장보다 뒤에 있으면 오류이다. *****
        this.name = name;
        this.phone = phone;
    }

    // 생성자 (construction)
    public PhoneBook(String name, String phone, String relation, String address, String memo) {
        this.name = name;
        this.phone = phone;
        this.relation = relation;
        this.address = address;
        this.memo = memo;
    }

    // setter
    public void setName(String name){
        this.name = name;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setRelation(String relation){
        this.relation = relation;
    }
    public void setMemo(String memo){
        this.memo = memo;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRelation() {
        return relation;
    }

    public String getMemo() {
        return memo;
    }

    public String getAddress() {
        return address;
    }


    // 출력 메소드 ver2
    public void output(){
        System.out.printf("이름 : %s, 전화번호 : %s, 관계 : %s, 주소 : %s, 메모 : %s %n",
                name, phone, relation, address, memo);
        // call by value임...? 왜
    }
}
