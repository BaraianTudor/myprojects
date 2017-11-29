package ro.fortech.demoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ro.fortech.demoapp.dao.AdvertisementDAO;
import ro.fortech.demoapp.dao.UserDAO;
import ro.fortech.demoapp.domain.SiteUser;
import ro.fortech.demoapp.domain.advertisment.Advertisement;
import ro.fortech.demoapp.exception.InvalidAdvertismentOwner;

@Service
public class AdvertisementService {

	private final static int PAGESIZE = 10;

	@Autowired
	private AdvertisementDAO advertisementDAO;

	@Autowired
	private UserDAO siteUserDAO;
	
	public void saveAdvertismentEntity(Advertisement advertisement) {
		advertisementDAO.save(advertisement);
	}

	public void saveAdvertisement(Advertisement advertisement, String username, String saveFileImage) {
		advertisement.setPicturePath(saveFileImage);
		advertisement.setOwner(siteUserDAO.findByEmail(username));
		advertisementDAO.save(advertisement);
	}

	public List<Advertisement> getLastSixEntries() {
		List<Advertisement> result = advertisementDAO.findTop6ByOrderByIdDesc();
		return result;
	}

	public Page<Advertisement> getAdByStringTitleOrBodyAndActiveTrue(String primarycat, String string, int pageNumber) {

		PageRequest request = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "id");

		return advertisementDAO.findByActiveTrueAndAdTypeContainingIgnoreCaseAndTitleContainingIgnoreCaseOrActiveTrueAndAdTypeContainingIgnoreCaseAndBodyContainingIgnoreCase(primarycat, string, primarycat, string, request);
	}

	public Advertisement findAdvertisementById(Long id) {
		return advertisementDAO.findById(id);

	}

	public void updateAdvertisement(Advertisement advertisement, String username, String saveFileImage)
			throws InvalidAdvertismentOwner {
		if (username.equals(advertisement.getOwner().getEmail())) {
			advertisement.setPicturePath(saveFileImage);
			advertisementDAO.save(advertisement);
		} else {
			throw new InvalidAdvertismentOwner();
		}

	}

	public void deleteAdvertisment(String username, Long id) throws InvalidAdvertismentOwner {
		if (username.equals(findAdvertisementById(id).getOwner().getEmail())) {
			advertisementDAO.delete(id);
		} else {
			throw new InvalidAdvertismentOwner();
		}
	}

	public List<Advertisement> getAllUserAds(SiteUser user) {
		return advertisementDAO.findByOwner(user);
	}

	public void deactivateAd(Advertisement advertisement) {
		if (advertisement.isActive()) {
			advertisement.setActive(false);
			saveAdvertismentEntity(advertisement);
		}
	}

	public void activateAd(Advertisement advertisement) {
		if (!advertisement.isActive()) {
			advertisement.setActive(true);
			saveAdvertismentEntity(advertisement);
		}
	}

}
