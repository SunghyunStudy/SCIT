import java.time.LocalDate;

public class BankAccount {
    // 멤버 변수(필드, 인스턴스변수)
    private String customerName;    // 고객명
    private String accountNo;       // 계좌번호
    private int balance;            // 잔액
    private LocalDate createDate;

    // 멤버 메소드 ==> 멤버 변수에 접근할 수 있도록 해주는 메소드임.
    // 접근 지정자 : private, public

    // 멤버 메소드 :
    // 우리멤버변수에 접근해서 값을 넣는 메소드 ==> setter
    // setter : 우리 멤버 하나에 접근해서 값을 넣음(=초기화)
    // SCBankTest에서 만든 print 메서드랑 같음.

    // 괄호 안의 변수는 지역변수임
    // 지역변수명이 멤버변수명과 같다면 지역변수가 우선이 되기 때문에
    // 우리멤버변수에 접근을 못함 -> 이때는 this를 써야됨
     public void setCustomerName(String customerName) {
         this.customerName = customerName;     // this : 우리 멤버를 가져온다.
     }
     public void setAccountNo(String accountNo) {
         this.accountNo = accountNo;
     }
     public void setBalance(int balance){
         this.balance = balance;
     }
     public void setCreateDate(LocalDate createDate){
         this.createDate = createDate;
     }

    // 우리멤버변수에 접근해서 값을 외부로 전달하는 메소드 ==> getter
    // getter :
    // getter는 this 안붙여도 됨. 지역변수가 없기 때문에. 헷갈릴게없다.
    public String getCustomerName(){
         return this.customerName;
    }
    public String getAccountNo(){
         return this.accountNo;
    }
    public int getBalance(){
         return this.balance;
    }
    public LocalDate getCreateDate(){
         return this.createDate;
    }

    public void output(){
        System.out.printf("고객명 : %s, 계좌번호 : %s, 잔액 : %d, 날짜 : %s", this.customerName, this.accountNo,  this.balance, this.createDate);
    }

}
