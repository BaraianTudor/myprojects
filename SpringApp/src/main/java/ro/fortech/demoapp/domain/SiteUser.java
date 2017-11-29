package ro.fortech.demoapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "site_users")
public class SiteUser extends BaseEntity {

	@Column(unique = true)
	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
			+ "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid mail addres.")
	private String email;

	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} long.")
	private String firstName;

	@Size(min = 2, max = 30, message = "Last name must be between {min} and {max} long.")
	private String lastName;

	
	private String password;

	@Transient
	@Size(min = 6, max = 30, message = "Password must be between {min} and {max} long.")
	private String passwordConfirmation;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Address address;

	@OneToMany(mappedBy = "sender")
	private List<Message> outbox;

	@OneToMany(mappedBy = "reciver")
	private List<Message> inbox;

	private boolean active;

	private String role;

	public SiteUser() {
		super();
		setActive(true);
	}

	/**
	 * @return the email
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	/**
	 * @param passwordConfirmation
	 *            the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the adress
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param adress
	 *            the adress to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the outbox
	 */
	public List<Message> getOutbox() {
		return outbox;
	}

	/**
	 * @param outbox
	 *            the outbox to set
	 */
	public void setOutbox(List<Message> outbox) {
		this.outbox = outbox;
	}

	/**
	 * @return the inbox
	 */
	public List<Message> getInbox() {
		return inbox;
	}

	/**
	 * @param inbox
	 *            the inbox to set
	 */
	public void setInbox(List<Message> inbox) {
		this.inbox = inbox;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

}
