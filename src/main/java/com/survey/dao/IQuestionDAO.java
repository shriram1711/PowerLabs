package com.survey.dao;

import java.util.List;

import com.survey.entity.Question;

public interface IQuestionDAO {

	Question getQuestionByID(Long questionId);

	List<Question> getAllQuestion();

	Long save(Question question);

	void update(Question question);

	void view(Question question);

	void delete(int questionId);

	List<Question> getAllQuestionBySurveyId(Long surveyId);
}
