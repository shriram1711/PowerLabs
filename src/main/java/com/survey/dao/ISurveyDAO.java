package com.survey.dao;

import java.util.List;

import com.survey.entity.Survey;

public interface ISurveyDAO {

	List<Survey> getSurvey();

	Survey createSurvey(Survey survey);

	Survey updateServey(int id, Survey survey);

	boolean deleteSurvey(int id);

	Survey getSurveyById(Long id);
}
