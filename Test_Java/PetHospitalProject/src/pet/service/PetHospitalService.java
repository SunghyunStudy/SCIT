package pet.service;

import pet.vo.Pet;

import java.util.List;

public interface PetHospitalService {
	public void create(Pet pet);
	public Pet retrieve(String id);
	public void delete(String id) ;
	public List<Pet> retrieveAll();
}
