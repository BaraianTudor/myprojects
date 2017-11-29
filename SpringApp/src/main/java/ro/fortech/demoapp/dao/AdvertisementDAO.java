package ro.fortech.demoapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;

import ro.fortech.demoapp.domain.SiteUser;
import ro.fortech.demoapp.domain.advertisment.Advertisement;

public interface AdvertisementDAO extends PagingAndSortingRepository<Advertisement, Long> {

	List<Advertisement> findTop6ByOrderByIdDesc();

	Page<Advertisement> findByActiveTrueAndAdTypeContainingIgnoreCaseAndTitleContainingIgnoreCaseOrActiveTrueAndAdTypeContainingIgnoreCaseAndBodyContainingIgnoreCase(String string, String string1, String string2, String string3, Pageable pageable);
	Advertisement findById(Long id);
	List<Advertisement> findByOwner(SiteUser user);

}
