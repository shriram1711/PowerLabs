package com.survey.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionId")
	private Long questionId;

	@Column
	private String question;

	@Column
	private String answer;

	@Transient
	private List<String> answers;

	private Long surveyId;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
