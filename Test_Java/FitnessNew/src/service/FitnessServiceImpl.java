package service;

import java.util.ArrayList;
import java.util.List;
import vo.Fitness;

public class FitnessServiceImpl implements FitnessService {

    // 회원 데이터를 저장할 리스트 (메모리 데이터베이스 역할)
    private List<Fitness> list = new ArrayList<>();

    /**
     * 회원 등록 기능 구현
     */
    @Override
    public boolean create(Fitness fitness) {
        // 먼저 동일한 아이디의 회원이 있는지 확인
        if (retrieve(fitness.getId()) != null) {
            // 이미 회원이 존재하면 등록 실패
            return false;
        }
        // 회원이 없으면 리스트에 추가하고 성공 반환
        list.add(fitness);
        return true;
    }

    /**
     * 아이디로 회원 조회 기능 구현
     */
    @Override
    public Fitness retrieve(String id) {
        // 리스트의 모든 회원을 반복해서 확인
        for (Fitness f : list) {
            if (f.getId().equals(id)) {
                // 아이디가 일치하는 회원을 찾으면 해당 객체 반환
                return f;
            }
        }
        // 반복문이 끝날 때까지 못 찾으면 null 반환
        return null;
    }

    /**
     * 회원 정보 수정 기능 구현 (키, 몸무게)
     */
    @Override
    public boolean update(Fitness fitness) {
        // 아이디로 수정할 회원을 먼저 찾음
        Fitness originalMember = retrieve(fitness.getId());

        if (originalMember != null) {
            // 회원이 존재하면, 전달받은 정보로 키와 몸무게를 덮어씀
            originalMember.setHeight(fitness.getHeight());
            originalMember.setWeight(fitness.getWeight());
            return true; // 수정 성공
        }

        // 회원이 없으면 수정 실패
        return false;
    }

    /**
     * 회원 삭제 기능 구현
     */
    @Override
    public boolean delete(String id) {
        // 아이디로 삭제할 회원을 먼저 찾음
        Fitness memberToDelete = retrieve(id);

        if (memberToDelete != null) {
            // 회원이 존재하면 리스트에서 삭제
            list.remove(memberToDelete);
            return true; // 삭제 성공
        }

        // 회원이 없으면 삭제 실패
        return false;
    }

    /**
     * 전체 회원 목록 조회 기능 구현
     */
    @Override
    public List<Fitness> retrieveAll() {
        // 저장된 리스트 자체를 그대로 반환
        return list;
    }
}