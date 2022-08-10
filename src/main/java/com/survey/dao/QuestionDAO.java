package com.survey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.entity.Question;

@Transactional
@Repository
public class QuestionDAO implements IQuestionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Question getQuestionByID(Long questionId) {
		return null;
	}

	@Override
	public List<Question> getAllQuestion() {
		return null;
	}

	@Override
	public Long save(Question question) {
		entityManager.persist(question);
		Question q = getLastInsertedQuestion();
		return q.getQuestionId();
	}

	@Override
	public void update(Question question) {

	}

	@Override
	public void view(Question question) {

	}

	@Override
	public void delete(int questionId) {

	}

	private Question getLastInsertedQuestion() {
		String hql = "from Question order by questionId DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Question question = (Question) query.getSingleResult();
		return question;
	}

	@Override
	public List<Question> getAllQuestionBySurveyId(Long surveyId) {
		String hql = "from Question where survey_id = ?";
		Query query = entityManager.createQuery(hql).setParameter(1, surveyId);
		List<Question> questions = query.getResultList();
		return questions;
	}
}
