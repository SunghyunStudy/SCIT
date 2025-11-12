package Exam_33;

public class Users {
    private String csn;
    private String name;
    private double weight;
    private double height;

    public Users(){}

    public Users(String csn, String name, double weight, double height) {
        this.csn = csn;
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    public void setCsn(String csn) {this.csn = csn;}

    public String getCsn() {return csn;}
}
