package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ro.fortech.demoapp.domain.advertisment.subsubcategory.SportSubcategory;

@Entity
@DiscriminatorValue("Sport")
public class Sport extends Advertisement {

	@Enumerated(EnumType.STRING)
	private SportSubcategory subCategory;

	public SportSubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SportSubcategory subCategory) {
		this.subCategory = subCategory;
	}

}
