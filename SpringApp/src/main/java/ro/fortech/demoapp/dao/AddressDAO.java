package ro.fortech.demoapp.dao;

import org.springframework.data.repository.CrudRepository;

import ro.fortech.demoapp.domain.Address;



public interface AddressDAO extends CrudRepository<Address, Long>{

}
