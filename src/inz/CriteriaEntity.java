package inz;

public class CriteriaEntity {
	int id_criteria;
	String name;
	int criteria_ratio_id_cr;

	@Override
	public String toString() {
		return "CriteriaEntity [id_criteria=" + id_criteria + ", name=" + name + ", criteria_ratio_id_cr="
				+ criteria_ratio_id_cr + "]";
	}

	public CriteriaEntity(int id_criteria, String name, int criteria_ratio_id_cr) {
		super();
		this.id_criteria = id_criteria;
		this.name = name;
		this.criteria_ratio_id_cr = criteria_ratio_id_cr;
	}
}
