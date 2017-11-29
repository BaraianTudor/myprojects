package ro.fortech.demoapp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import ro.fortech.demoapp.domain.advertisment.Advertisement;
import ro.fortech.demoapp.domain.advertisment.enums.AutoBrand;
import ro.fortech.demoapp.domain.advertisment.enums.Color;
import ro.fortech.demoapp.domain.advertisment.enums.Courency;
import ro.fortech.demoapp.domain.advertisment.enums.FuelType;
import ro.fortech.demoapp.domain.advertisment.enums.JobType;
import ro.fortech.demoapp.domain.advertisment.enums.ProduceState;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.AutoSubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.BusinessSubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.ElectronicSubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.FashionSubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.GardeningSubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.IndustrySubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.JobSubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.PetSubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.PropertySubcategory;
import ro.fortech.demoapp.domain.advertisment.subsubcategory.SportSubcategory;
import ro.fortech.demoapp.exception.InvalidAdvertismentOwner;
import ro.fortech.demoapp.service.AdvertisementService;
import ro.fortech.demoapp.service.ImageFileService;

@Controller
@RequestMapping("/editad")
@SessionAttributes("advertisement")
public class AdvertisementEditController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private ImageFileService imageFileService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAdvertisment(Model model, @RequestParam(name = "id") Long id) {

		Advertisement advertisement = advertisementService.findAdvertisementById(id);

		switch (advertisement.getAdType().toLowerCase()) {
		case "auto":

			model.addAttribute("subCategory", AutoSubcategory.values());
			model.addAttribute("autoBrand", AutoBrand.values());
			model.addAttribute("fuelType", FuelType.values());
			break;
		case "business":

			model.addAttribute("subCategory", BusinessSubcategory.values());
			break;
		case "electronics":

			model.addAttribute("subCategory", ElectronicSubcategory.values());
			model.addAttribute("prodState", ProduceState.values());
			break;
		case "fashion":

			model.addAttribute("subCategory", FashionSubcategory.values());
			model.addAttribute("prodState", ProduceState.values());
			break;
		case "gardening":

			model.addAttribute("subCategory", GardeningSubcategory.values());
			model.addAttribute("prodState", ProduceState.values());
			break;
		case "industry":

			model.addAttribute("subCategory", IndustrySubcategory.values());
			break;
		case "job":

			model.addAttribute("subCategory", JobSubcategory.values());
			model.addAttribute("jobType", JobType.values());
			break;
		case "pet":

			model.addAttribute("subCategory", PetSubcategory.values());
			break;
		case "sport":

			model.addAttribute("subCategory", SportSubcategory.values());
			break;
		case "property":

			model.addAttribute("subCategory", PropertySubcategory.values());
			break;
		}

		model.addAttribute("template", "advertisement");
		model.addAttribute("color", Color.values());
		model.addAttribute("courency", Courency.values());
		model.addAttribute("advertisement", advertisement);
		model.addAttribute("page", advertisement.getAdType().toLowerCase());

		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addAuto(Model model, @ModelAttribute("advertisement") @Valid Advertisement advertisement,
			BindingResult result, @RequestParam("file") MultipartFile file) {

		model.addAttribute("template", "home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		try {
			advertisementService.updateAdvertisement(advertisement, username, imageFileService.saveFileImage(file, username));
		} catch (InvalidAdvertismentOwner e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		advertisementService.getLastSixEntries();

		return "redirect:/";
	}

}
