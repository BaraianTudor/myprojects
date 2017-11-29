package ro.fortech.demoapp.domain.advertisment;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DiscriminatorOptions;

import ro.fortech.demoapp.domain.BaseEntity;
import ro.fortech.demoapp.domain.SiteUser;
import ro.fortech.demoapp.domain.advertisment.enums.Courency;

@Entity
@Table(name = "Advertisement")
@Inheritance
@DiscriminatorColumn(name = "ADD_TYPE")
@DiscriminatorOptions(force = true)
public class Advertisement extends BaseEntity {

	@Size(min = 10, message = "Title must be at least {min} long.")
	private String title;

	@Size(min = 5, max = 1000, message = "Description must be between {min} and {max} long.")
	private String body;

	private Double price;

	@Enumerated(EnumType.STRING)
	private Courency courency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUBLISHER_ID")
	private SiteUser owner;

	@Column(name = "ADD_TYPE", insertable = false, updatable = false)
	private String adType;

	private boolean active;

	private String picturePath;

	public Advertisement() {
		super();
		setActive(true);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Courency getCourency() {
		return courency;
	}

	public void setCourency(Courency courency) {
		this.courency = courency;
	}

	public SiteUser getOwner() {
		return owner;
	}

	public void setOwner(SiteUser owner) {
		this.owner = owner;
	}

	public String getAdType() {
		return adType;
	}

	public void setAdType(String adType) {
		this.adType = adType;
	}

	/**
	 * @return the picturePath
	 */
	public String getPicturePath() {
		return picturePath;
	}

	/**
	 * @param picturePath
	 *            the picturePath to set
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Advertisement [title=" + title + ", body=" + body + ", price=" + price + ", courency=" + courency
				+ ", owner=" + owner + ", adType=" + adType + "]";
	}

}
