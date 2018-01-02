package inz;

public class QuestionnaireEntity {
	Integer id_questionnaire;
	String name;
	int service_id_service;

	public QuestionnaireEntity(Integer id_questionnaire, String name, int service_id_service) {
		super();
		this.id_questionnaire = id_questionnaire;
		this.name = name;
		this.service_id_service = service_id_service;
	}

	@Override
	public String toString() {
		return "Questionnaire [id_questionnaire=" + id_questionnaire + ", name=" + name + "]";
	}
}
