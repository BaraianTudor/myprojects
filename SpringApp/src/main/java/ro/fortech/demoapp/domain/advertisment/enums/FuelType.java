package ro.fortech.demoapp.domain.advertisment.enums;

public enum FuelType {

	BENZINA("Benzina"), DIESEL("Diesel"), GPL("GPL"), HIBRID("Hibrid"), ELECTRIC("Electric");

	private String name;

	private FuelType(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	
	
	
}
