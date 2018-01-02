package inz;

public class CriteriaObject {
	int id;
	String name;
	Double value;

	public CriteriaObject(int id, String name, Double value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return "CriteriaObject [id=" + id + ", name=" + name + ", value=" + value + "]";
	}
}
