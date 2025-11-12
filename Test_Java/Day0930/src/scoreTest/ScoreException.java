// 성격이 60점 미만이면 Exception을 사용하여 메시지 출력...

package scoreTest;

public class ScoreException extends Exception {
    public ScoreException(String message) {
        super(message);
    }
}
