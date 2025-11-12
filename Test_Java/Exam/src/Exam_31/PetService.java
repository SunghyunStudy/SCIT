package Exam_31;

public class PetService {
    PetVo[] list = new PetVo[50];
    int count;

    public PetService(){}

    public boolean insert(PetVo pet){
        if(count >= 50) return false;

        list[count++] = pet;
        return true;
    }

    public PetVo[] search(){
        return list;
    }

    public int count(){
        return count;
    }
}
