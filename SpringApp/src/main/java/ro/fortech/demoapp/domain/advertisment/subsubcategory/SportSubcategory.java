package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum SportSubcategory {

	FITNESS("Fitness - Supliments"), SPORT_EQUIPMENT("Sport equipment"), OTHER("Other");

	private String name;

	private SportSubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
