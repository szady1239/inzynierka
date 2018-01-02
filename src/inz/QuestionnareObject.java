package inz;

public class QuestionnareObject {
	int id;
	String name;
	String service;

	public QuestionnareObject(int id, String name, String service) {
		super();
		this.id = id;
		this.name = name;
		this.service = service;
	}

	@Override
	public String toString() {
		return "QuestionnareObject [id=" + id + ", name=" + name + ", service=" + service + "]";
	}

}
