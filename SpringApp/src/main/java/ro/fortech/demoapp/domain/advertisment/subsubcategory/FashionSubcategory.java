package ro.fortech.demoapp.domain.advertisment.subsubcategory;

public enum FashionSubcategory {

	WOMEN_CLOTHING("Women clothing"), MEN_CLOTHING("Men Clothing"), WOMES_SHOES("Women shoes"), MEN_SHOES(
			"Men shoes"), ACCESSORIES("Accessories"), COSMETICS("Cosmetics - Parfumes"), OTHERS("Others");

	private String name;

	private FashionSubcategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
