package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;

import ro.fortech.demoapp.domain.advertisment.enums.Color;
import ro.fortech.demoapp.domain.advertisment.enums.ProduceState;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.FashionSubcategory;

@Entity
@DiscriminatorValue("Fashion")
public class Fashion extends Advertisement {

	@Enumerated(EnumType.STRING)
	private FashionSubcategory subCategory;

	@Min(value = 1, message =" Invalid size")
	private int size;

	private ProduceState prodState;

	private Color color;

	private String Brand;

	public FashionSubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(FashionSubcategory subCategory) {
		this.subCategory = subCategory;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ProduceState getProdState() {
		return prodState;
	}

	public void setProdState(ProduceState prodState) {
		this.prodState = prodState;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

}
