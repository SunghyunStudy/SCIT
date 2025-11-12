package pet.service;

import java.util.ArrayList;
import java.util.List;

import pet.vo.Bird;
import pet.vo.Mammal;
import pet.vo.Pet;

public class PetHospitalServiceImpl implements PetHospitalService {
	List<Pet> list = new ArrayList<>();

	/**
	 * 애완동물의 정보를 전달받아 ArrayList에 저장
	 * @param pet
	 * @return 등록여부를 반환
	 */
	@Override
	public void create(Pet pet) {
       // Code here
        list.add(pet);
	}

	/**
	 * Primary Key에 해당하는 id를 전달받아 그 id의 애완동물 정보를 검색하여 반환
	 * @param id: PK
	 * @return 찾으려는 애완동물의 정보, 조회하려는 애완동물이 없으면 Exception 발생
	 */
	@Override
	public Pet retrieve(String id) {
        // Code Here
        for(int i=0; i<list.size(); ++i) {
            if(list.get(i).getId().equals(id)) return list.get(i);
        }

        return null;
	}

	/**
	 * 탈퇴할 회원의 PK를 전달받아 목록에서 삭제
	 * @param id: 탈퇴할 회원의 PK
	 * @return : 삭제결과 반환
	 */
	@Override
	public void delete(String id)  {
        // Code here
        Pet p = retrieve(id);

        if (p != null) {
            list.remove(p);
        }
	}

	/**
	 * 회원 전체의 목록을 조회할 수 있도록 배열을 반환
	 * @return 회원 목록
	 */
	@Override
	public List<Pet> retrieveAll() {
		// Code here
        return list;
	}
}







