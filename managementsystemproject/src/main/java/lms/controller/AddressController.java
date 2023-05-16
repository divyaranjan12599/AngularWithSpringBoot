package lms.controller;

import lms.serviceImpl.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

	@Autowired
	private AddressServiceImpl addressService;

	@GetMapping("/country")
	public List<String> getAllCountry() {
		return addressService.getAllCountryName();
	}

	@GetMapping("/state")
	public List<String> getAllState(@RequestParam(name = "countryname") String countryName) {
		return addressService.getAllStateName(countryName);
	}

	@GetMapping("/city")
	public List<String> getAllCity(@RequestParam(name = "statename") String stateName) {
		return addressService.getAllCityName(stateName);
	}
}
