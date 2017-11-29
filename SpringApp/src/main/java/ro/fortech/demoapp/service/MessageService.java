package ro.fortech.demoapp.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ro.fortech.demoapp.dao.AdvertisementDAO;
import ro.fortech.demoapp.dao.MessageDAO;
import ro.fortech.demoapp.dao.UserDAO;
import ro.fortech.demoapp.domain.Message;
import ro.fortech.demoapp.domain.SiteUser;

@Service
public class MessageService {

	private final static int PAGESIZE = 10;

	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private SiteUserService siteUserService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AdvertisementDAO advertisementDAO;

	/**
	 * Saves message to DB.
	 * 
	 * @param message
	 */

	public void saveMessage(Message message) {
		messageDAO.save(message);
	}

	/**
	 * 
	 * @param pageNumber
	 * @return
	 */

	/**
	 * returns a new message with the sender set as the person authenticated and as
	 * the reciver the owner of the ad.
	 * 
	 * @param senderUserName
	 * @param id
	 * @return
	 */

	public Message newMessage(String senderUserName, Long id) {

		Message newMessage = new Message();
		newMessage.setReciver(advertisementDAO.findById(id).getOwner());
		newMessage.setSender(userDAO.findByEmail(senderUserName));
		return newMessage;
	}

	/**
	 * returns a new message with the sender set as the person authenticated and as
	 * the reciver the owner of the message wich he replays
	 * 
	 * @param senderUserName
	 * @param id
	 * @return
	 */

	public Message replyMessage(String senderUserName, Long id) {

		Message newMessage = new Message();
		newMessage.setReciver(messageDAO.findById(id).getSender());
		newMessage.setSender(userDAO.findByEmail(senderUserName));
		return newMessage;
	}

	/**
	 * Saves a sent message to the DB.
	 * 
	 * @param message
	 */

	public void sendMessage(Message message) {
		message.setReciverVisibility(true);
		message.setSenderVisibility(true);
		messageDAO.save(message);

	}

	/**
	 * 
	 * @param pageNumber
	 * @param siteUserName
	 * @return a page of messages that have as a reciver the user loged in and has
	 *         reciverVisibility set to true.
	 */

	public Page<Message> getUserInbox(int pageNumber, String siteUserName) {

		SiteUser owner = userDAO.findByEmail(siteUserName);

		PageRequest request = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "creationTime");

		return messageDAO.findByReciverAndReciverVisibilityTrue(owner, request);

	}

	/**
	 * 
	 * @param pageNumber
	 * @param siteUserName
	 * @return a page of messages that have as a sender the user logeed in and has
	 *         senderVisibility set to true.
	 */

	public Page<Message> getUserOutbox(int pageNumber, String siteUserName) {

		SiteUser owner = userDAO.findByEmail(siteUserName);

		PageRequest request = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "creationTime");

		return messageDAO.findBySenderAndSenderVisibilityTrue(owner, request);

	}

	/**
	 * 
	 * @param pageNumber
	 * @param username
	 * @returna a page of messages that have as a reciver the user logeed in and has
	 *          deleteBoolean set to true.
	 */

	public Page<Message> getUserTrash(int pageNumber, String username) {

		SiteUser owner = userDAO.findByEmail(username);

		PageRequest request = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "creationTime");

		return messageDAO.findByReciverAndDeleteBooleanTrue(owner, request);

	}

	/**
	 * 
	 * @param pageNumber
	 * @param username
	 * @return a page of messages that have as a reciver the user logeed in and has
	 *         starBoolean set to true.
	 */

	public Page<Message> getUserImportant(int pageNumber, String username) {

		SiteUser owner = userDAO.findByEmail(username);

		PageRequest request = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "creationTime");

		return messageDAO.findByReciverAndStarBooleanTrue(owner, request);

	}

	/**
	 * 
	 * @param id
	 * @return true if message starBoolean is set succesfuly to true
	 */

	public boolean setMessageImportant(Long id) {

		Message message = messageDAO.findById(id);
		if (message != null) {
			message.setStarBoolean(true);
			messageDAO.save(message);
			return true;

		}

		return false;

	}

	/**
	 * 
	 * @param id
	 * @return true if message starBoolean is set succesfuly to false
	 */

	public boolean removeImportant(Long id) {

		Message message = messageDAO.findById(id);
		if (message != null) {
			message.setStarBoolean(false);
			messageDAO.save(message);
			return true;

		}

		return false;

	}

	/**
	 * 
	 * @param id
	 * @return true if message reciverVisibility is set succesfuly to true and
	 *         deleteBoolean is set to false
	 */

	public boolean RestoreMessage(Long id) {

		Message message = messageDAO.findById(id);
		if (message != null) {
			message.setReciverVisibility(true);
			message.setDeleteBoolean(false);
			messageDAO.save(message);
			return true;

		}

		return false;

	}

	public boolean deleteMessageInbox(Long id) {

		Message message = messageDAO.findById(id);
		if (message != null) {
			message.setReciverVisibility(false);
			message.setDeleteBoolean(true);
			messageDAO.save(message);
			return true;

		}

		return false;

	}

	public boolean deleteMessageOutbox(Long id) {

		Message message = messageDAO.findById(id);
		if (message != null) {
			message.setSenderVisibility(false);
			messageDAO.save(message);
			return true;

		}

		return false;

	}

	public boolean deleteMessageTrash(Long id) {

		Message message = messageDAO.findById(id);
		if (message != null) {
			message.setDeleteBoolean(false);
			messageDAO.save(message);
			return true;
		}

		return false;

	}

	public boolean deleteMessageImportant(Long id) {

		Message message = messageDAO.findById(id);
		if (message != null) {
			message.setStarBoolean(false);
			message.setReciverVisibility(false);
			message.setSenderVisibility(false);
			message.setDeleteBoolean(true);
			messageDAO.save(message);
			return true;
		}

		return false;

	}

	public Message getMessageById(Long id) {
		return messageDAO.findOne(id);
	}

	public Message readMessage(Long id, String senderUserName) {
		Message message = getMessageById(id);
		if (message.getReciver().getEmail().equals(senderUserName)
				|| message.getSender().getEmail().equals(senderUserName)) {

			message.setReadBoolean(true);
			messageDAO.save(message);
		} else {
			return new Message();
		}

		return message;
	}

	public Integer getBoxInfo(String username) {
		Integer info =  messageDAO.findByReciverAndReadBooleanAndReciverVisibility(siteUserService.loadUser(username), false, true).size();

		return info;
	}

	public void deleteMessageList(List<Long> messagIds, String box) {
		Iterator it = messagIds.iterator();
		switch (box) {
		case "inbox":
			while (it.hasNext()) {
				deleteMessageInbox((Long) it.next());
			}
			break;
		case "outbox":
			while (it.hasNext()) {
				deleteMessageOutbox((Long) it.next());
			}
			break;
		case "important":
			while (it.hasNext()) {
				deleteMessageImportant((Long) it.next());
			}
			break;
		case "trash":
			while (it.hasNext()) {
				deleteMessageTrash((Long) it.next());
			}
			break;
		}
	}

	public void setMessageListImportant(List<Long> messagIds, String box) {
		Iterator it = messagIds.iterator();
		if (box.equals("important")) {
			while (it.hasNext()) {
				removeImportant((Long) it.next());
			}
		} else {

			while (it.hasNext()) {
				setMessageImportant((Long) it.next());
			}
		}

	}

	public void setRestoreMesseges(List<Long> messagIds) {
		Iterator it = messagIds.iterator();
		while (it.hasNext()) {
			RestoreMessage((Long) it.next());
		}

	}

}
