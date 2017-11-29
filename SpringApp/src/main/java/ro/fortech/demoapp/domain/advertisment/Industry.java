package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ro.fortech.demoapp.domain.advertisment.subsubcategory.IndustrySubcategory;

@Entity
@DiscriminatorValue("Industry")
public class Industry extends Advertisement {

	@Enumerated(EnumType.STRING)
	private IndustrySubcategory subCategory;

	public IndustrySubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(IndustrySubcategory subCategory) {
		this.subCategory = subCategory;
	}

}
