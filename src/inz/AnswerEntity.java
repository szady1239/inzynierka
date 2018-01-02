package inz;

public class AnswerEntity {
	int id_answer;
	boolean value;

	public AnswerEntity(int id_answer, boolean value) {
		super();
		this.id_answer = id_answer;
		this.value = value;
	}

	@Override
	public String toString() {
		return "AnswerEntity [id_answer=" + id_answer + ", value=" + value + "]";
	}
}
