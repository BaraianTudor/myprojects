package ro.fortech.demoapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ro.fortech.demoapp.domain.SiteUser;


public interface UserDAO extends PagingAndSortingRepository<SiteUser, Long>{
	
	SiteUser findByEmail(String email);

	Object findByRole(String string);

	Page<SiteUser> findAll(Pageable pageable);

	SiteUser findById(Long id);

}
