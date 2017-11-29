package ro.fortech.demoapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.fortech.demoapp.service.SiteUserService;

@Controller
public class AdminUserController {

	@Autowired
	private SiteUserService siteUserService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String editSiteUserAddress(Model model, @RequestParam(name = "page", defaultValue = "0") int pageNumber) {

		model.addAttribute("element", "admin");

		model.addAttribute("template", "user");

		model.addAttribute("userList", siteUserService.getUsers(pageNumber));

		return "index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String saveSiteUserAddress(HttpServletRequest request, Model model,
			@RequestParam(required = false, value = "userIDs") List<Long> userIDs,
			@RequestParam(name = "page", defaultValue = "0") int pageNumber) {

		model.addAttribute("element", "admin");

		model.addAttribute("template", "user");

		if (userIDs != null) {
			if (request.getParameter("action").equals("Activate")) {
				siteUserService.activateUserList(userIDs);

			} else if (request.getParameter("action").equals("Deactivate")) {
				siteUserService.deActivateUserList(userIDs);

			}
		}

		model.addAttribute("userList", siteUserService.getUsers(pageNumber));

		return "index";
	}

}
