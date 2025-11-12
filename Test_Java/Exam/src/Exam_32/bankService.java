package Exam_32;

import java.util.ArrayList;

public class bankService {
    ArrayList<User> userList =  new ArrayList<>();


    public boolean check(String accNo){
        for(User user : userList){
            if(user.getAccNo().equals(accNo)) return true;
        }
        return false;
    }

    public void userAdd(User user){
        userList.add(user);
    }

    public ArrayList<User> print(){
        return userList;
    }

    public void inMoney(String accNo, int depositPrice){
        for(User user : userList){
            if(user.getAccNo().equals(accNo)){
                int sum = user.getBalance() + depositPrice;
                user.setBalance(sum);
            }
        }
    }

    public boolean outMoney(String accNo, int depositPrice){
        for(User user : userList){
            if(user.getAccNo().equals(accNo)){
                int balance = user.getBalance() - depositPrice;
                if (balance < 0) return false;
                user.setBalance(balance);
            }
        }
        return true;
    }

}
