package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum GardeningSubcategory {

	FURNITURE("Furniture"), GARDEN("Garden"), CONSTRUCTION_MATERIALS(
			"construction materials"), HEATING_ELECTRIC_SANITARY(
					"Heating - Electric - Sanitary"), TOOLS("Tools"), OTHER("Others");

	private String name;

	private GardeningSubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
