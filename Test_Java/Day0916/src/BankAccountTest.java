import java.time.LocalDate;

public class BankAccountTest {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
//        ba.customerName = "홍길동";  // private때매 생성이 안됨
        ba.setCustomerName("홍길동");
        ba.setAccountNo("123123");
        ba.setBalance(123);
        ba.setCreateDate(LocalDate.of(2025, 9,19));

        ba.output();
    }
}
