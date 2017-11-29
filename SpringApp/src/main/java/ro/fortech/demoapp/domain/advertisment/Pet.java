package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ro.fortech.demoapp.domain.advertisment.subsubcategory.PetSubcategory;

@Entity
@DiscriminatorValue("Pet")
public class Pet extends Advertisement {

	@Enumerated(EnumType.STRING)
	private PetSubcategory subCategory;

	public PetSubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(PetSubcategory subCategory) {
		this.subCategory = subCategory;
	}

}
