package ro.fortech.demoapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;

import ro.fortech.demoapp.domain.SiteUser;
import ro.fortech.demoapp.domain.advertisment.Advertisement;

public interface AdvertisementDAO extends PagingAndSortingRepository<Advertisement, Long> {

	List<Advertisement> findTop6ByOrderByIdDesc();
	Page<Advertisement> findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(String string, String string1, Pageable pageable);
	Advertisement findById(Long id);
	List<Advertisement> findByOwner(SiteUser user);

}
