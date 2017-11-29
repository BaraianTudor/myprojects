package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum BusinessSubcategory {

	AUTO_SERVICE("Auto service - transportation"), CONSTRUCTION("Construction"), ELECTRONICS_SERVICE(
			"Electronics Service"), EVENTS("Wedings and other events"), COURSES(
					"Courses"), BEAUTY("Beauty salons"), ACCOUNTING("Accounting"), OHERS("Others");

	private String name;

	private BusinessSubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
