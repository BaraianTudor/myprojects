package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ro.fortech.demoapp.domain.advertisment.subsubcategory.BusinessSubcategory;

@Entity
@DiscriminatorValue("Business")
public class Business extends Advertisement {

	@Enumerated(EnumType.STRING)
	private BusinessSubcategory subCategory;

	public BusinessSubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(BusinessSubcategory subCategory) {
		this.subCategory = subCategory;
	}

}
