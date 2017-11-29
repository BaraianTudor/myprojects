package ro.fortech.demoapp.controller;

import java.util.Arrays;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.fortech.demoapp.dao.AddressDAO;
import ro.fortech.demoapp.domain.Address;
import ro.fortech.demoapp.domain.SiteUser;
import ro.fortech.demoapp.domain.enums.City;
import ro.fortech.demoapp.exception.PasswordNotMatchEcxception;
import ro.fortech.demoapp.service.EmailService;
import ro.fortech.demoapp.service.SiteUserService;

@Controller
public class SiteUserController {

	@Autowired
	private SiteUserService siteUserService;

	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private EmailService emailService;

	@RequestMapping("/login")
	public String login(Model model) {

		model.addAttribute("template", "login");

		return "index";
	}

	@RequestMapping("/loginerror")
	public String loginError(Model model) {

		model.addAttribute("template", "loginerror");

		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(Model model) {

		model.addAttribute("template", "register");

		SiteUser siteUser = new SiteUser();

		City[] city = City.values();

		Arrays.sort(city);

		model.addAttribute("city", city);

		model.addAttribute("siteUser", siteUser);

		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addSiteUser(Model model, @Valid SiteUser siteUser, BindingResult result) {

		model.addAttribute("template", "register");

		if (siteUserService.passwordValidation(siteUser.getPassword(), siteUser.getPasswordConfirmation())) {

			if (!result.hasErrors()) {
				if (siteUserService.saveSiteUser(siteUser) == true) {
					
					emailService.sendNewUser(siteUser.getEmail());

					model.addAttribute("template", "usersuccess");

				} else {

					model.addAttribute("template", "userexists");
				}
			}
		} else {
			try {
				throw new PasswordNotMatchEcxception();
			} catch (PasswordNotMatchEcxception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "index";
	}

	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public String editSiteUser(Model model) {

		model.addAttribute("element", "edituser");

		model.addAttribute("template", "user");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		SiteUser siteUser = siteUserService.loadUser(auth.getName());

		City[] city = City.values();

		model.addAttribute("city", city);

		model.addAttribute("siteUser", siteUser);

		return "index";
	}

	@RequestMapping(value = "/editaddress", method = RequestMethod.GET)
	public String editSiteUserAddress(Model model) {

		model.addAttribute("element", "editaddress");

		model.addAttribute("template", "user");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Address address = siteUserService.loadUser(auth.getName()).getAddress();

		City[] city = City.values();

		model.addAttribute("city", city);

		model.addAttribute("address", address);

		return "index";
	}

	@RequestMapping(value = "/editaddress", method = RequestMethod.POST)
	public String saveSiteUserAddress(Model model, @ModelAttribute("address") Address address) {

		model.addAttribute("element", "editaddress");

		model.addAttribute("template", "user");

		addressDAO.save(address);

		model.addAttribute("address", address);

		return "index";
	}

}
