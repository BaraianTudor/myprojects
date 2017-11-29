package ro.fortech.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.fortech.demoapp.exception.InvalidAdvertismentOwner;
import ro.fortech.demoapp.service.AdvertisementService;
import ro.fortech.demoapp.service.EmailService;

@Controller

public class ViewDeleteAdControler {

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/showad", method = RequestMethod.GET)
	public String viewAd(Model model, @RequestParam(name = "id") Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		emailService.someoneJustViewedYourAdd(advertisementService.findAdvertisementById(id).getOwner().getEmail());

		model.addAttribute("loggedUser", username);
		model.addAttribute("advertisement", advertisementService.findAdvertisementById(id));
		model.addAttribute("template", "showad");

		return "index";
	}

	@RequestMapping(value = "/deletead", method = RequestMethod.GET)
	public String deleteAd(Model model, @RequestParam(name = "id") Long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		try {
			advertisementService.deleteAdvertisment(username, id);
		} catch (InvalidAdvertismentOwner e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}

}
