package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum ElectronicSubcategory {

	PHONES("Phones"), PHONE_ACCESORIES("Phone accesories"), COMPUTERS("Computers and laptops"), TV(
			"Tv - Audio -Video"), HOME_APPLIANCE(
					"Home appliances"), CAMERAS("Photo and video cameras"), GAMES("Console games"), OTHERS("Others");

	private String name;

	private ElectronicSubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
