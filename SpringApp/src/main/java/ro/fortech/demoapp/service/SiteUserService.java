package ro.fortech.demoapp.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ro.fortech.demoapp.dao.UserDAO;
import ro.fortech.demoapp.domain.SiteUser;
import ro.fortech.demoapp.domain.advertisment.Advertisement;
import ro.fortech.demoapp.exception.UsernameNotActiveException;

@Service
public class SiteUserService implements UserDetailsService {

	private final static int PAGESIZE = 40;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void updateUser(SiteUser siteUser) {
		userDAO.save(siteUser);
	}

	public boolean saveSiteUser(SiteUser siteUser) {
		if (userDAO.findByRole("ROLE_ADMIN") == null) {
			if (userDAO.findByEmail(siteUser.getEmail()) == null) {

				siteUser.setRole("ROLE_ADMIN");
				siteUser.setPassword(passwordEncoder.encode(siteUser.getPassword()));
				userDAO.save(siteUser);
				return true;
			}
		} else if (userDAO.findByEmail(siteUser.getEmail()) == null) {

			siteUser.setRole("ROLE_USER");
			siteUser.setPassword(passwordEncoder.encode(siteUser.getPassword()));
			userDAO.save(siteUser);
			return true;
		}
		return false;
	}

	public SiteUser loadUser(String email) {
		return userDAO.findByEmail(email);
	}

	public SiteUser findById(Long id) {
		return userDAO.findById(id);
	}

	public void activateUser(Long id) {
		SiteUser user = findById(id);
		Iterator<Advertisement> it = advertisementService.getAllUserAds(user).iterator();

		while (it.hasNext()) {
			advertisementService.activateAd(it.next());
		}
		user.setActive(true);
		updateUser(user);

	}

	public void deActivateUser(Long id) {
		SiteUser user = findById(id);
		Iterator<Advertisement> it = advertisementService.getAllUserAds(user).iterator();
		while (it.hasNext()) {
			advertisementService.deactivateAd(it.next());
		}

		user.setActive(false);
		updateUser(user);

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		SiteUser siteUser = userDAO.findByEmail(email);

		if (siteUser == null) {
			throw new UsernameNotFoundException("User not registred");
		} else if (!siteUser.isActive()) {
			try {
				throw new UsernameNotActiveException("User deactivated");
			} catch (UsernameNotActiveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(siteUser.getRole());

			String password = siteUser.getPassword();

			return new User(email, password, auth);
		}
		return null;
	}

	public Page<SiteUser> getUsers(int pageNumber) {

		PageRequest request = new PageRequest(pageNumber, PAGESIZE, Sort.Direction.DESC, "id");

		return userDAO.findAll(request);
	}

	public void activateUserList(List<Long> userIDs) {

		Iterator<Long> it = userIDs.iterator();
		while (it.hasNext()) {

			activateUser(it.next());

		}

	}

	public void deActivateUserList(List<Long> userIDs) {
		Iterator<Long> it = userIDs.iterator();
		while (it.hasNext()) {
			deActivateUser(it.next());

		}

	}

	public boolean passwordValidation(String string1, String string2) {
		if (string1.equals(string2)) {
			return true;
		}
		return false;
	}

}
