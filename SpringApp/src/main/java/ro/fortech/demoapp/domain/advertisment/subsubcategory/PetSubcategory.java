package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum PetSubcategory {

	CATS_DOGS("Cats - Dogs"), SERVICES_ACCESORIES("Pet services - Accesories"), OTHER("Other");

	private String name;

	private PetSubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
