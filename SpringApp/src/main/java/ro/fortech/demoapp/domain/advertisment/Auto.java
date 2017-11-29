package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;

import ro.fortech.demoapp.domain.advertisment.enums.AutoBrand;
import ro.fortech.demoapp.domain.advertisment.enums.Color;
import ro.fortech.demoapp.domain.advertisment.enums.FuelType;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.AutoSubcategory;

@Entity
@DiscriminatorValue("Auto")
public class Auto extends Advertisement {

	@Enumerated(EnumType.STRING)
	private AutoBrand autoBrand;

	@Enumerated(EnumType.STRING)
	private AutoSubcategory subCategory;

	private String model;

	@Enumerated(EnumType.STRING)
	private FuelType fuelType;

	@Enumerated(EnumType.STRING)
	private Color color;

	@Min(value = 1990, message ="Invalid build year!")
	private int buildYear;

	@Min(value = 0, message ="Invalid input")
	private int rulaj;

	public AutoBrand getAutoBrand() {
		return autoBrand;
	}

	public void setAutoBrand(AutoBrand autoBrand) {
		this.autoBrand = autoBrand;
	}

	public AutoSubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(AutoSubcategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public int getRulaj() {
		return rulaj;
	}

	public void setRulaj(int rulaj) {
		this.rulaj = rulaj;
	}

}
