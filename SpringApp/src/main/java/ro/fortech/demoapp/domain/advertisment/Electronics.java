package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ro.fortech.demoapp.domain.advertisment.enums.ProduceState;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.ElectronicSubcategory;

@Entity
@DiscriminatorValue("Electronics")
public class Electronics extends Advertisement {

	@Enumerated(EnumType.STRING)
	private ElectronicSubcategory subCategory;

	private ProduceState prodState;

	public ElectronicSubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(ElectronicSubcategory subCategory) {
		this.subCategory = subCategory;
	}

	public ProduceState getProdState() {
		return prodState;
	}

	public void setProdState(ProduceState prodState) {
		this.prodState = prodState;
	}

}
