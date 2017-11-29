package ro.fortech.demoapp.domain.enums;

public enum City {
	B("București"), IS("Iași"), CJ("Cluj"), TM("Timiș"), CT("Constanța"), DJ("Dolj"), GL("Galați"), BV("Brașov"), PH(
			"Prahova"), BR("Brăila"), BH("Bihor"), BC("Bacău"), AR("Arad"), AG("Argeș"), SB("Sibiu"), MS("Mureș"), MM(
					"Maramureș"), BZ("Buzău"), SM("Satu Mare"), BT("Botoșani"), VL("Vâlcea"), SV(
							"Suceava"), NT("Neamț"), MH("Mehedinți"), VN("Vrancea"), GJ("Gorj"), TL(
									"Tulcea"), DB("Dâmbovița"), CS("Caraș-Severin"), BN("Bistrița-Năsăud"), OT(
											"Olt"), HD("Hunedoara"), CL("Călărași"), VS("Vaslui"), GR("Giurgiu"), AB(
													"Alba"), SJ("Sălaj"), CV("Covasna"), IV(
															"Ialomița"), TR("Teleorman"), HR("Harghita"), IF("Ilfov");

	private String name;

	private City(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	

}
