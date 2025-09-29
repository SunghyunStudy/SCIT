package fitness.service;

import java.util.ArrayList;
import java.util.List;

import fitness.vo.Fitness;

public class FitnessServiceImpl implements FitnessService{
	List<Fitness> list = new ArrayList<>();

	/**
	 * 회원 한사람의 정보를 전달받아 배열에 저장
	 * @param fitness
	 * @return 등록여부를 반환
	 */
	@Override
	public void create(Fitness fitness) throws Exception {
		String id = fitness.getId();
        System.out.println("테스트 : " + fitness.isGender());
		for(int i=0; i<list.size(); ++i) 
			if(list.get(i).getId().equals(id)) throw new Exception("## 회원가입을 할 수 없습니다.");

		list.add(fitness);
	}

	/**
	 * Primary Key에 해당하는 id를 전달받아 그 id의 회원정보를 검색하여 반환
	 * @param id: PK
	 * @return 찾은 한 사람의 정보, 조회하려는 회원이 없으면 Exception 발생
	 */
	@Override
	public Fitness retrieve(String id) throws Exception {

		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getId().equals(id)) return list.get(i);
		}

		throw new Exception("## 회원이 존재하지 않습니다. ID를 확인하세요");
	}

	/**
	 * 전달받은 멤버의 정보를 수정하는 메소드
	 * @param fitness: 수정할 정보를 담고 있는 객체
	 * @return 수정 결과를 반환
	 */
	@Override
	public void update(Fitness fitness) throws Exception {
		String id = fitness.getId();

		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getId().equals(id)) {
				// 키, 몸무게 수정 (
				double h = fitness.getHeight();
				double w = fitness.getWeight();

				list.get(i).setHeight(h);
				list.get(i).setWeight(w);

				return;
			}	
		}
		
		throw new Exception("## 회원정보 수정작업이 중단되었습니다.");
	}

	/**
	 * 탈퇴할 회원의 PK를 전달받아 목록에서 삭제
	 * @param id: 탈퇴할 회원의 PK
	 * @return : 삭제결과 반환
	 */	
	@Override
	public void delete(String id) throws Exception {
		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getId().equals(id)) {
				list.remove(i);
				return;
			}
		}
		throw new Exception("## 회원 정보 삭제 작업이 중단되었습니다.");
	}

	/**
	 * 회원 전체의 목록을 조회할 수 있도록 배열을 반환
	 * @return 회원 목록
	 */
	@Override
	public List<Fitness> retrieveAll() {
		
		return list;
	}


}







