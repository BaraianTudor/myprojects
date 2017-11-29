package ro.fortech.demoapp.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Calendar creationTime;

	@Column
	private Calendar modificationTime;

	@PrePersist
	public void prePersist() {
		Calendar now = new GregorianCalendar();
		this.modificationTime = now;
		this.creationTime = now;
	}

	@PreUpdate
	public void preUpdate() {
		this.modificationTime = new GregorianCalendar();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}

	public Calendar getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Calendar modificationTime) {
		this.modificationTime = modificationTime;
	}

}
