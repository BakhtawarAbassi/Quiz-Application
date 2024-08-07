package com.example.quizApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizApplication.model.QuestionWrapper;
import com.example.quizApplication.model.Response;
import com.example.quizApplication.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	QuizService quizService;

@PostMapping("/create")
public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
	
	return  quizService.createQuiz(category, numQ, title);
		
}
@GetMapping("get/{id}")
public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
	return quizService.getQuizQuestion(id);
}
@PostMapping("/submit/{id}")
public ResponseEntity<Integer> createQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
	
	return quizService.calculateResult(id,response);
	  
		
}

}
