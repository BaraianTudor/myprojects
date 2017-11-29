package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum IndustrySubcategory {

	AGRICULTURAL_MACHINERY("Agricultural machinery"), PLANTS("Cereals - Plants - Trees"), OTHER("Other");

	private String name;

	private IndustrySubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
