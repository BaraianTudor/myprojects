package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum AutoSubcategory {

	CAR("Cars"), MOTORCYCLE("Motorcycle"), TRUK("Truks"), OTHER("Others");

	private String name;

	private AutoSubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
