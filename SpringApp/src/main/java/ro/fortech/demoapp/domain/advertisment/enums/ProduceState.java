package ro.fortech.demoapp.domain.advertisment.enums;

public enum ProduceState {

	NEW("New"), USED("Used");

	private String name;

	private ProduceState(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
