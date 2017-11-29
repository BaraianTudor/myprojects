package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ro.fortech.demoapp.domain.advertisment.enums.JobType;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.JobSubcategory;

@Entity
@DiscriminatorValue("Job")
public class Job extends Advertisement {

	@Enumerated(EnumType.STRING)
	private JobSubcategory subCategory;

	private JobType jobType;

	public JobSubcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(JobSubcategory subCategory) {
		this.subCategory = subCategory;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

}
