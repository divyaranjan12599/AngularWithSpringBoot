package lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lms.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
