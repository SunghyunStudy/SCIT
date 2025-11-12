package scoreTest;

// 평균이 60점 미만이면 익셉션 처리하시오

public class Score implements Comparable<Score>{
    private String id;
    private String name;
    private int it;
    private int japan;
    private double avg;

    public Score() {
    }

    public Score( String id, String name, int it, int japan) {
        this.japan = japan;
        this.it = it;
        this.name = name;
        this.id = id;
        this.avg = (it + japan) / 2.0;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIt() {
        return it;
    }

    public void setIt(int it) {
        this.it = it;
    }

    public int getJapan() {
        return japan;
    }

    public void setJapan(int japan) {
        this.japan = japan;
    }

    public double getAvg(){
        return avg;
    }

    @Override
    public String toString() {
        String temp = String.format("학번 : %s  이름 : %s  it : %d  일본어 : %d  평균 : %.2f", id, name, it, japan, avg);
        return temp;
    }

    @Override
    // 학번순으로 정렬하기 위한 코드
    public int compareTo(Score o) {
        return id.compareTo(o.getId());
    }
}

