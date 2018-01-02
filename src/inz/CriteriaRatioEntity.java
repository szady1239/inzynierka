package inz;

public class CriteriaRatioEntity {
	int id_cr;
	Double criteria_value;

	public CriteriaRatioEntity(int id_cr, Double criteria_value) {
		super();
		this.id_cr = id_cr;
		this.criteria_value = criteria_value;
	}

	@Override
	public String toString() {
		return "CriteriaRatioEntity [id_cr=" + id_cr + ", criteria_value=" + criteria_value + "]";
	}
}
