package com.example.quizApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.quizApplication.model.Question;

public interface QuestionDao extends JpaRepository<Question,Integer>{


	List<Question> findByCategory(String category);

	@Query(value="SELECT * FROM question  q Where q.category=:category  LIMIT :numQ",nativeQuery=true)
	List<Question> findRandomQuestionsByCategory(int numQ, String category);
}
