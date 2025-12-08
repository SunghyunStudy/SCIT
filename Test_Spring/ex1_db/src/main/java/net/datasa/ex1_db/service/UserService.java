package net.datasa.ex1_db.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.ex1_db.domain.dto.UserDTO;
import net.datasa.ex1_db.domain.entity.UserEntity;
import net.datasa.ex1_db.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository ur;
	
	
	public void insert(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setId(dto.getId());
		entity.setPw(dto.getPw());
		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());
		
		ur.save(entity);
	}
	
	public UserDTO idCheck(String id, String pw){
		UserEntity entity = ur.findById(id).orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
		UserDTO dto = new UserDTO();
		
		if(entity.getId().equals(id) && entity.getPw().equals(pw)){
			dto.setId(entity.getId());
			dto.setPw(entity.getPw());
			dto.setName(entity.getName());
			dto.setPhone(entity.getPhone());
			
			return dto;
		}
		return null;
	}
	
	public List<UserDTO> selectAll() {
		List<UserEntity> entityList = ur.findAll();
		List<UserDTO> dtoList = new ArrayList<>();
		
		for(UserEntity entity : entityList){
			UserDTO dto = new UserDTO();
			dto.setId(entity.getId());
			dto.setPw(entity.getPw());
			dto.setName(entity.getName());
			dto.setPhone(entity.getPhone());
			dtoList.add(dto);
		}
		return dtoList;
	}
}
