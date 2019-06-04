package idu.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import idu.cs.entity.QuestionEntity;

public interface QuestionRepository 
	extends JpaRepository<QuestionEntity, Long> {
	/*
	QuestionEntity findByUserId(String userId); // id : 자동증가번호, userId : 회원가입아이디
	List<QuestionEntity> findByName(String name);
	List<QuestionEntity> findByNameOrderByIdAsc(String name);
	List<QuestionEntity> findByCompany(String company);
	*/
}
