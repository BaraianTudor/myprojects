package ro.fortech.demoapp.controller;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.fortech.demoapp.domain.Message;
import ro.fortech.demoapp.service.EmailService;
import ro.fortech.demoapp.service.MessageService;

@Controller
public class MessageController {
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/compose", method = RequestMethod.GET)
	public String sendMessage(Model model, @RequestParam(name = "id") Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String senderUserName = auth.getName();

		Message message = messageService.newMessage(senderUserName, id);

		model.addAttribute("toEmail", message.getReciver().getEmail());

		model.addAttribute("boxInfo", messageService.getBoxInfo(senderUserName));
		model.addAttribute("template", "messagebox");
		model.addAttribute("message", message);
		model.addAttribute("element", "compose");
		model.addAttribute("template", "messagebox");
		return "index";

	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String replyMessage(Model model, @RequestParam(name = "id") Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String senderUserName = auth.getName();

		Message message = messageService.replyMessage(senderUserName, id);

		model.addAttribute("toEmail", message.getReciver().getEmail());

		model.addAttribute("boxInfo", messageService.getBoxInfo(senderUserName));
		model.addAttribute("message", message);
		model.addAttribute("element", "compose");
		model.addAttribute("template", "messagebox");
		return "index";

	}

	@RequestMapping(value = "/compose", method = RequestMethod.POST)
	public String sendMessage(Model model, @Valid Message message, BindingResult result) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String senderUserName = auth.getName();

		model.addAttribute("boxInfo", messageService.getBoxInfo(senderUserName));
		model.addAttribute("template", "messagebox");

		if (!result.hasErrors()) {
			messageService.sendMessage(message);
			emailService.revicedMessage(message.getReciver().getEmail(), message.getSender().getEmail());
			model.addAttribute("element", "messagesent");
		} else {
			model.addAttribute("element", "messagenotsent");
		}

		return "index";

	}

	@RequestMapping(value = "/messagebox", method = RequestMethod.GET)
	public String getMessageBox(Model model, @RequestParam(name = "box") String box,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Page<Message> messageList = null;
		switch (box) {
		case "inbox":
			messageList = messageService.getUserInbox(pageNumber, username);
			break;
		case "outbox":
			messageList = messageService.getUserOutbox(pageNumber, username);
			break;
		case ("important"):
			messageList = messageService.getUserImportant(pageNumber, username);
			break;
		case ("trash"):
			messageList = messageService.getUserTrash(pageNumber, username);
			break;
		}
		model.addAttribute("boxInfo", messageService.getBoxInfo(username));
		model.addAttribute("box", box);
		model.addAttribute("date", new GregorianCalendar());
		model.addAttribute("messageList", messageList);
		model.addAttribute("element", "inbox");
		model.addAttribute("template", "messagebox");

		return "index";
	}

	@RequestMapping(value = "/readmessage", method = RequestMethod.GET)
	public String readMessage(Model model, @RequestParam(name = "id") Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String senderUserName = auth.getName();

		Message message = messageService.readMessage(id, senderUserName);

		model.addAttribute("fromEmail", message.getSender().getEmail());

		model.addAttribute("boxInfo", messageService.getBoxInfo(senderUserName));
		model.addAttribute("message", message);
		model.addAttribute("element", "readmessage");
		model.addAttribute("template", "messagebox");
		return "index";

	}

	@RequestMapping(value = "/messageMove", method = RequestMethod.GET)
	public String deleteMessages(HttpServletRequest request, Model model,
			@RequestParam(required = false, value = "messagIds") List<Long> messagIds,
			@RequestParam(name = "box") String box) {
		

		if (messagIds != null) {
			if (request.getParameter("action").equals("Delete")) {
				System.out.println("sterg");
				messageService.deleteMessageList(messagIds, box);
			} else if (request.getParameter("action").equals("Important")) {
				messageService.setMessageListImportant(messagIds, box);
			} else if (request.getParameter("action").equals("Restore")) {
				messageService.setRestoreMesseges(messagIds);
			}

		}

		return "redirect:/messagebox?box=inbox";
	}
	
	@RequestMapping("/messages/unread")
	public boolean getNumberOfUnreadMessages() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String senderUserName = auth.getName();
		if (messageService.getBoxInfo(senderUserName) >0){
			return true;
		}
		return false;
		
	}

}
