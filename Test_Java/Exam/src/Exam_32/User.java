package Exam_32;

public class User {
    private String name;
    private String accNo;
    private int balance;

    public User() {}

    public User(String name, String accNo, int balance){
        this.name = name;
        this.accNo = accNo;
        this.balance = balance;
    }

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void setAccNo(String accNo){this.accNo = accNo;}
    public String getAccNo(){return accNo;}

    public void setBalance(int balance){this.balance = balance;}
    public int getBalance(){return balance;}

    @Override
    public String toString() {
        String output = String.format("성명 : %s  계좌번호 : %s  잔고 : %d", name, accNo, balance);
        return output;
    }
}
