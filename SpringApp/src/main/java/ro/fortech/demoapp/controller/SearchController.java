package ro.fortech.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.fortech.demoapp.domain.advertisment.Advertisement;
import ro.fortech.demoapp.domain.advertisment.enums.AdvertisementMainCategoty;
import ro.fortech.demoapp.service.AdvertisementService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private AdvertisementService advertisementService;

	@RequestMapping(method = RequestMethod.GET)
	public String getSearch(Model model, @RequestParam(name = "page", defaultValue = "0") int pageNumber,
			@RequestParam(name = "search") String search) {

		Page<Advertisement> searchResult = advertisementService.getAdByStringTitleOrBody(search, pageNumber);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("search", search);

		model.addAttribute("pageNumber", pageNumber);

		model.addAttribute("loggedUser", username);

		model.addAttribute("searchResultSize", searchResult.getTotalElements());

		model.addAttribute("searchResult", searchResult);

		model.addAttribute("primaryCat", AdvertisementMainCategoty.values());

		model.addAttribute("searchString", search);

		model.addAttribute("template", "search");

		return "index";
	}

}
