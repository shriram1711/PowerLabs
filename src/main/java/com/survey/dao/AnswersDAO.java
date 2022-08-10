package com.survey.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.entity.Answers;
import com.survey.entity.Question;
import com.survey.entity.Survey;

@Transactional
@Repository
public class AnswersDAO implements IAnswersDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Answers save(Answers ans) {
		entityManager.persist(ans);
		Answers a = getLastInsertedAnswer();
		return a;
	}

	private Answers getLastInsertedAnswer() {
		String hql = "from Answers order by answer_id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Answers answer = (Answers) query.getSingleResult();
		return answer;
	}

	@Override
	public List<Answers> getAllAnswersByQuestionId(Long questionId) {
		String hql = "from Answers where question_id = ?";
		Query query = entityManager.createQuery(hql).setParameter(1, questionId);
		@SuppressWarnings("unchecked")
		List<Answers> answers = query.getResultList();
		return answers;
	}

	@Override
	public void takeServey(Survey survey) {
		List<Question> questions = survey.getQuestions();
		if (questions != null) {
			for (Question qs : questions) {
				Answers ans = new Answers();
				ans.setAnswer(qs.getAnswer());
				ans.setQuestionId(qs.getQuestionId());
				this.save(ans);
			}
		}
	}

}
