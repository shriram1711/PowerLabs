package com.survey.dao;

import java.util.List;

import com.survey.entity.Answers;
import com.survey.entity.Survey;

public interface IAnswersDAO {

	Answers save(Answers ans);

	List<Answers> getAllAnswersByQuestionId(Long questionId);

	void takeServey(Survey survey);

}