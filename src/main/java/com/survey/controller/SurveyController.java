package com.survey.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.survey.dao.IAnswersDAO;
import com.survey.dao.IQuestionDAO;
import com.survey.entity.Answers;
import com.survey.entity.Question;
import com.survey.entity.Survey;
import com.survey.services.ISurveyService;

@Controller
@RequestMapping("surveyservice")
public class SurveyController {

	@Autowired
	private ISurveyService surveyService;

	@Autowired
	private IQuestionDAO questionDAO;

	@Autowired
	private IAnswersDAO answerDAO;

	@GetMapping("survey")
	public ResponseEntity<List<Survey>> getServeys() {
		List<Survey> surveys = surveyService.getSurvey();
		return new ResponseEntity<List<Survey>>(surveys, HttpStatus.OK);

	}

	@GetMapping("survey/{id}")
	public ResponseEntity<Survey> getSurvey(@PathVariable("id") Long id) {
		Survey survey = surveyService.getSurveyById(id);
		List<Question> qList = questionDAO.getAllQuestionBySurveyId(id);
		survey.setQuestions(qList);
		for (Question q : qList) {
			List<Answers> ans = answerDAO.getAllAnswersByQuestionId(q.getQuestionId());
			List<String> aList = new ArrayList<>();
			for (Answers a : ans) {
				aList.add(a.getAnswer());
			}
			q.setAnswers(aList);
		}
		return new ResponseEntity<Survey>(survey, HttpStatus.OK);
	}

	@PostMapping("survey")
	public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
		Survey s = surveyService.createSurvey(survey);
		List<Question> qList = s.getQuestions();
		for (Question q : qList) {
			q.setSurveyId(s.getId());
			questionDAO.save(q);

			List<Answers> answers = survey.getAnswers();
			if (answers != null) {
				for (Answers ans : answers) {
					ans.setAnswer(ans.getAnswer());
					ans.setQuestionId(q.getQuestionId());
					answerDAO.save(ans);
				}
			}
		}
		return new ResponseEntity<Survey>(s, HttpStatus.OK);

	}

	@PutMapping("survey/{id}")
	public ResponseEntity<Survey> takeSurvey(@PathVariable("id") Long id, @RequestBody Survey survey) {
		answerDAO.takeServey(survey);
		Survey s = surveyService.getSurveyById(id);
		return new ResponseEntity<Survey>(s, HttpStatus.OK);
	}

}
