package inz;

public class ServiceEntity {
	public ServiceEntity(int id_service, String name, double quality_score) {
		super();
		this.id_service = id_service;
		this.name = name;
		this.quality_score = quality_score;
	}

	int id_service = 0;
	String name = "Nic";
	double quality_score = 0.0;

	@Override
	public String toString() {
		return "Service [id_service=" + id_service + ", name=" + name + ", quality_score=" + quality_score + "]";
	}

}
