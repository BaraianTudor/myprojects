package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ro.fortech.demoapp.domain.advertisment.enums.ProduceState;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.GardeningSubcategory;

@Entity
@DiscriminatorValue("Gardening")
public class Gardening extends Advertisement {

	@Enumerated(EnumType.STRING)
	private GardeningSubcategory subCategory;

	private ProduceState prodState;

	public GardeningSubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(GardeningSubcategory subCategory) {
		this.subCategory = subCategory;
	}

	public ProduceState getProdState() {
		return prodState;
	}

	public void setProdState(ProduceState prodState) {
		this.prodState = prodState;
	}

}
