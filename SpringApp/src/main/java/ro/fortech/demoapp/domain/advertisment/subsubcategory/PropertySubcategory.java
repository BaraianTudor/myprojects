package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum PropertySubcategory {

	APARTMENT_SALE("Apartment sale"), APARTMENT_RENT("Apartment rent"), HOUSE_SALE("House sale"), HOUSE_RENT(
			"House rent"), LAND("Land"), OFFICE_SPACE("Office/commercial space"), OTHERS("Others");

	private String name;

	private PropertySubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
