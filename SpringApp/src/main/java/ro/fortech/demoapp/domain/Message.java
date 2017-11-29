package ro.fortech.demoapp.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "message")
public class Message extends BaseEntity {

	@Size(min = 3, max = 30, message = "Message title must be between {min} and {max} long.")
	private String messageTitle;

	@Size(min = 10, max = 400, message = "Message must be between {min} and {max} long.")
	private String messageBody;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SENDER_ID")
	private SiteUser sender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECIVER_ID")
	private SiteUser reciver;

	private boolean senderVisibility;
	private boolean reciverVisibility;
	private boolean readBoolean;
	private boolean deleteBoolean;
	private boolean starBoolean;

	/**
	 * @return the messageTitle
	 */
	public String getMessageTitle() {
		return messageTitle;
	}

	/**
	 * @param messageTitle
	 *            the messageTitle to set
	 */
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	/**
	 * @return the messageBody
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * @param messageBody
	 *            the messageBody to set
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	/**
	 * @return the sender
	 */
	public SiteUser getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(SiteUser sender) {
		this.sender = sender;
	}

	/**
	 * @return the reciver
	 */
	public SiteUser getReciver() {
		return reciver;
	}

	/**
	 * @param reciver
	 *            the reciver to set
	 */
	public void setReciver(SiteUser reciver) {
		this.reciver = reciver;
	}

	/**
	 * @return the senderVisibility
	 */
	public boolean isSenderVisibility() {
		return senderVisibility;
	}

	/**
	 * @param senderVisibility
	 *            the senderVisibility to set
	 */
	public void setSenderVisibility(boolean senderVisibility) {
		this.senderVisibility = senderVisibility;
	}

	/**
	 * @return the reciverVisibility
	 */
	public boolean isReciverVisibility() {
		return reciverVisibility;
	}

	/**
	 * @param reciverVisibility
	 *            the reciverVisibility to set
	 */
	public void setReciverVisibility(boolean reciverVisibility) {
		this.reciverVisibility = reciverVisibility;
	}

	public boolean isReadBoolean() {
		return readBoolean;
	}

	public void setReadBoolean(boolean readBoolean) {
		this.readBoolean = readBoolean;
	}

	public boolean isDeleteBoolean() {
		return deleteBoolean;
	}

	public void setDeleteBoolean(boolean deleteBoolean) {
		this.deleteBoolean = deleteBoolean;
	}

	public boolean isStarBoolean() {
		return starBoolean;
	}

	public void setStarBoolean(boolean starBoolean) {
		this.starBoolean = starBoolean;
	}

}
