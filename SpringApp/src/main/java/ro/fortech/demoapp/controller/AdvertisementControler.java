package ro.fortech.demoapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import ro.fortech.demoapp.domain.advertisment.Auto;
import ro.fortech.demoapp.domain.advertisment.Business;
import ro.fortech.demoapp.domain.advertisment.Electronics;
import ro.fortech.demoapp.domain.advertisment.Fashion;
import ro.fortech.demoapp.domain.advertisment.Gardening;
import ro.fortech.demoapp.domain.advertisment.Industry;
import ro.fortech.demoapp.domain.advertisment.Job;
import ro.fortech.demoapp.domain.advertisment.Pet;
import ro.fortech.demoapp.domain.advertisment.Property;
import ro.fortech.demoapp.domain.advertisment.Sport;
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
import ro.fortech.demoapp.service.AdvertisementService;
import ro.fortech.demoapp.service.ImageFileService;

@Controller
@RequestMapping("/new")
@SessionAttributes("advertisement")
public class AdvertisementControler {

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	private ImageFileService imageFileService;

	@Value("{photo.upload.directory}")
	private String photoUploadDirectory;

	@RequestMapping(method = RequestMethod.GET)
	public String getAdvertisment(Model model, @RequestParam(name = "page") String page) {

		Advertisement advertisement = null;

		switch (page) {
		case "auto":
			advertisement = new Auto();
			model.addAttribute("subCategory", AutoSubcategory.values());
			model.addAttribute("autoBrand", AutoBrand.values());
			model.addAttribute("fuelType", FuelType.values());
			break;
		case "business":
			advertisement = new Business();
			model.addAttribute("subCategory", BusinessSubcategory.values());
			break;
		case "electronics":
			advertisement = new Electronics();
			model.addAttribute("subCategory", ElectronicSubcategory.values());
			model.addAttribute("prodState", ProduceState.values());
			break;
		case "fashion":
			advertisement = new Fashion();
			model.addAttribute("subCategory", FashionSubcategory.values());
			model.addAttribute("prodState", ProduceState.values());
			break;
		case "gardening":
			advertisement = new Gardening();
			model.addAttribute("subCategory", GardeningSubcategory.values());
			model.addAttribute("prodState", ProduceState.values());
			break;
		case "industry":
			advertisement = new Industry();
			model.addAttribute("subCategory", IndustrySubcategory.values());
			break;
		case "job":
			advertisement = new Job();
			model.addAttribute("subCategory", JobSubcategory.values());
			model.addAttribute("jobType", JobType.values());
			break;
		case "pet":
			advertisement = new Pet();
			model.addAttribute("subCategory", PetSubcategory.values());
			break;
		case "sport":
			advertisement = new Sport();
			model.addAttribute("subCategory", SportSubcategory.values());
			break;
		case "property":
			advertisement = new Property();
			model.addAttribute("subCategory", PropertySubcategory.values());
			break;
		}

		model.addAttribute("template", "advertisement");
		model.addAttribute("color", Color.values());
		model.addAttribute("courency", Courency.values());
		model.addAttribute("advertisement", advertisement);
		model.addAttribute("page", page);

		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addAuto(Model model, @ModelAttribute("advertisement") @Valid Advertisement advertisement,
			BindingResult result, @RequestParam("file") MultipartFile file, @RequestParam(name = "page") String page) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		if (!result.hasErrors()) {

			advertisementService.saveAdvertisement(advertisement, username,
					imageFileService.saveFileImage(file, username));
			model.addAttribute("template", "home");
			advertisementService.getLastSixEntries();
			return "redirect:/";

		}
		switch (page) {
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
		model.addAttribute("page", page);

		return "index";
	}

}
