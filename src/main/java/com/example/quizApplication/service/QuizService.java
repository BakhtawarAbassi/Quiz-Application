package com.example.quizApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizApplication.dao.QuestionDao;
import com.example.quizApplication.dao.QuizDao;
import com.example.quizApplication.model.Question;
import com.example.quizApplication.model.QuestionWrapper;
import com.example.quizApplication.model.Quiz;
import com.example.quizApplication.model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;
	

	public ResponseEntity<String> createQuiz(String category,int numQ,String title){
		List<Question> questions= questionDao.findRandomQuestionsByCategory(numQ,category);
		Quiz quiz =new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Successfull",HttpStatus.CREATED);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
		// TODO Auto-generated method stub
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Question> questionFromDb=quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser=new ArrayList();
		for(Question q:questionFromDb) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()); 
					questionForUser.add(qw);
		}
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}


	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		List<Question> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response:responses) {
			
		if(response.getResponse().equals(questions.get(i).getRightAnswer()))
			right++;
			
		i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
