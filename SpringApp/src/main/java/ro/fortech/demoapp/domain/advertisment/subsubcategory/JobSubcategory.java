package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum JobSubcategory {

	SALES("Sale Agent"), AGRICULTURE("Agriculture"), BABYSITTER("Baby sitter"), CALLCENTER("Call center"), CLOTHING(
			"Clothing manufacter"), COSMETICS("Cosmetics"), EDUCATION("Education"), ENTERTAINMENT(
					"Entertainment"), FINANCE("Finance"), CONSTRUCTION("Construction"), INTERNSHIP("Internship"), IT(
							"IT"), COMMERCE("Commerce"), PRODUCTIONWORKERS("Production workers"), SECURITY(
									"Security"), ADMINISTRATIVE_PERSONNEL("Administrative personnel"), HEALTH_CARE(
											"Health care personnel"), TRAVEL("Travel Personnel");

	private String name;

	private JobSubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
