package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;

import ro.fortech.demoapp.domain.advertisment.subsubcategory.PropertySubcategory;

@Entity
@DiscriminatorValue("Property")
public class Property extends Advertisement {

	@Enumerated(EnumType.STRING)
	private PropertySubcategory subCategory;

	@Min(value = 0, message =" Invalid size input")
	private Double size;

	@Min(value = 1800, message =" Invalid build year!")
	private int buildYear;

	public PropertySubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(PropertySubcategory subCategory) {
		this.subCategory = subCategory;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

}
