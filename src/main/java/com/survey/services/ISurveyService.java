package com.survey.services;

import java.util.List;

import com.survey.entity.Survey;

public interface ISurveyService {

	List<Survey> getSurvey();

	Survey getSurveyById(Long id);

	Survey createSurvey(Survey survey);

	Survey updateServey(int id, Survey survey);

	boolean deleteSurvey(int id);

}
