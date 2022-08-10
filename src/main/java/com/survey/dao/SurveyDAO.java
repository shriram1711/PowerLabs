package com.survey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.entity.Question;
import com.survey.entity.Survey;

@Transactional
@Repository
public class SurveyDAO implements ISurveyDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private IQuestionDAO questionDAO;

	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> getSurvey() {
		String hql = "FROM Survey as atcl ORDER BY atcl.id";
		return (List<Survey>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Survey getSurveyById(Long id) {
		Survey s = entityManager.find(Survey.class, id);
		List<Question> questions = questionDAO.getAllQuestionBySurveyId(s.getId());
		s.setQuestions(questions);
		return s;
	}

	@Override
	public Survey updateServey(int id, Survey survey) {
		return null;
	}

	@Override
	public boolean deleteSurvey(int id) {
		return false;
	}

	@Override
	public Survey createSurvey(Survey survey) {
		entityManager.persist(survey);
		Survey s = getLastInsertedSurvey();
		return s;
	}

	private Survey getLastInsertedSurvey() {
		String hql = "from Survey order by id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Survey survey = (Survey) query.getSingleResult();
		return survey;
	}
}
