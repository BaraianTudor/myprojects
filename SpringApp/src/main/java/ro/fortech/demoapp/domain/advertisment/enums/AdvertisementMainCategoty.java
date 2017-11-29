package ro.fortech.demoapp.domain.advertisment.enums;

public enum AdvertisementMainCategoty {

	AUTO("Auto"), BUSINESS("Business"), ELECTRONICS("Electronics"), FASHION("Fashion"), GARDENING(
			"Gardening"), INDUSTRY("Industry"), JOB("Job"), PET("Pet"), PROPERTY("Property"), SPORT("Sport");

	private String name;

	private AdvertisementMainCategoty(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
