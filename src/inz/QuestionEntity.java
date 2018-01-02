package inz;

public class QuestionEntity {
	int id_question;
	String description;
	int criteria_id_criteria;
	int questionare_id_questionnaire;
	int answer_id_answer;

	public QuestionEntity(int id_question, String description, int criteria_id_criteria,
			int questionare_id_questionnaire, int answer_id_answer) {
		super();
		this.id_question = id_question;
		this.description = description;
		this.criteria_id_criteria = criteria_id_criteria;
		this.questionare_id_questionnaire = questionare_id_questionnaire;
		this.answer_id_answer = answer_id_answer;
	}

	@Override
	public String toString() {
		return "QuestionEntity [id_question=" + id_question + ", description=" + description + ", criteria_id_criteria="
				+ criteria_id_criteria + ", questionare_id_questionnaire=" + questionare_id_questionnaire
				+ ", answer_id_answer=" + answer_id_answer + "]";
	}

}
