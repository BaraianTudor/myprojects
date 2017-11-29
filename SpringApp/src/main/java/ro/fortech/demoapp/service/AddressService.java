package ro.fortech.demoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.fortech.demoapp.dao.AddressDAO;
import ro.fortech.demoapp.domain.Address;

@Service
public class AddressService {

	@Autowired
	private AddressDAO addressDAO;
	
	public boolean saveAddress(Address address) {
		addressDAO.save(address);
		return true;
	}
}
