package com.survey.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.dao.ISurveyDAO;
import com.survey.entity.Survey;

@Service
public class SurveyService implements ISurveyService {

	@Autowired
	private ISurveyDAO dao;

	@Override
	public List<Survey> getSurvey() {
		return dao.getSurvey();
	}

	@Override
	public Survey getSurveyById(Long id) {
		return dao.getSurveyById(id);
	}

	@Override
	public Survey createSurvey(Survey survey) {
		return dao.createSurvey(survey);
	}

	@Override
	public Survey updateServey(int id, Survey survey) {
		return null;
	}

	@Override
	public boolean deleteSurvey(int id) {
		return false;
	}

}
