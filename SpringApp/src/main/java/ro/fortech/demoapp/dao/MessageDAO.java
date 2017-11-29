package ro.fortech.demoapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ro.fortech.demoapp.domain.Message;
import ro.fortech.demoapp.domain.SiteUser;

public interface MessageDAO extends PagingAndSortingRepository<Message, Long> {

	Page<Message> findByReciverAndReciverVisibilityTrue(SiteUser owner, Pageable pageable);

	Page<Message> findBySenderAndSenderVisibilityTrue(SiteUser owner, Pageable pageable);

	Message findById(Long id);

	Page<Message> findByReciverAndDeleteBooleanTrue(SiteUser owner, Pageable pageable);

	Page<Message>  findByReciverAndStarBooleanTrue(SiteUser owner, Pageable pageable);

	List<Long> findByReciverAndReadBooleanAndReciverVisibility(SiteUser username, boolean b, boolean c);

}
