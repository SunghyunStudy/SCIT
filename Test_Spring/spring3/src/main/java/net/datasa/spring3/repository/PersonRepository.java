package net.datasa.spring3.repository;

import net.datasa.spring3.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {
	// JpaRepository<사용하고자하는 Entity명, 해당 Entity의 키 DataType>
}
