package Exam_30;

public class Car {
    private String name;
    private String carNo;
    private String phoneNo;
    private String location;

    public Car(){}

    public Car(String name, String carNo, String phoneNo, String location) {
        this.name = name;
        this.carNo = carNo;
        this.phoneNo = phoneNo;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        String print = String.format("\n 이름 : %s\n" +
                                    "차번호 : %s\n" +
                                    "전화번호 : %s\n" +
                                    "위치 : %s", name, carNo, phoneNo, location);
        return print;
    }
}
