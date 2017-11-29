package ro.fortech.demoapp.domain.advertisment.enums;

public enum Color {

	BLACK("Black"), BLUE("Blue"), CYAN("Cyan"), DARK_GRAY("DarkGrey"), GRAY("Grey"), GREEN("Green"), LIGHT_GRAY(
			"Light Gray"), MAGENTA(
					"Magenta"), ORANGE("Orange"), PINK("Pink"), RED("Red"), WHITE("White"), YELLOW("Yellow");

	private String name;

	private Color(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
