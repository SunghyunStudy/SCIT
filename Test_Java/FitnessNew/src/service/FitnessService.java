package service;

import java.util.List;
import vo.Fitness;

public interface FitnessService {
    /**
     * 회원 1명의 정보를 받아 저장합니다.
     * @param fitness 등록할 회원 정보
     * @return 등록 성공 시 true, 아이디 중복으로 실패 시 false
     */
    public boolean create(Fitness fitness);

    /**
     * 아이디를 받아 해당하는 회원 정보를 반환합니다.
     * @param id 검색할 회원의 아이디
     * @return 검색된 회원 객체, 없을 경우 null
     */
    public Fitness retrieve(String id);

    /**
     * 수정할 회원 정보를 받아 기존 정보를 업데이트합니다.
     * @param fitness 수정할 정보를 담은 객체 (키, 몸무게)
     * @return 수정 성공 시 true, 해당 아이디의 회원이 없어 실패 시 false
     */
    public boolean update(Fitness fitness);

    /**
     * 아이디를 받아 해당하는 회원을 목록에서 삭제합니다.
     * @param id 삭제할 회원의 아이디
     * @return 삭제 성공 시 true, 해당 아이디의 회원이 없어 실패 시 false
     */
    public boolean delete(String id);

    /**
     * 전체 회원 목록을 반환합니다.
     * @return 회원 정보가 담긴 List
     */
    public List<Fitness> retrieveAll();
}