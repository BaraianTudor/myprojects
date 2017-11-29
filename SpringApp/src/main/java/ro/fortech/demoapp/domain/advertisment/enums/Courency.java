package ro.fortech.demoapp.domain.advertisment.enums;

public enum Courency {

	RON("Ron"), USD("Usd"), EURO("EURO");

	private String name;

	private Courency(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
