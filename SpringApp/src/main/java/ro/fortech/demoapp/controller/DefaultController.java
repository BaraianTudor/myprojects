package ro.fortech.demoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.fortech.demoapp.domain.advertisment.Advertisement;
import ro.fortech.demoapp.service.AdvertisementService;

@Controller
public class DefaultController {

	@Autowired
	private AdvertisementService advertisementService;

	@RequestMapping("/")
	public String getHome(Model model) {

		List<Advertisement> result = advertisementService.getLastSixEntries();

		model.addAttribute("result", result);

		model.addAttribute("template", "home");
		return "index";
	}

}
