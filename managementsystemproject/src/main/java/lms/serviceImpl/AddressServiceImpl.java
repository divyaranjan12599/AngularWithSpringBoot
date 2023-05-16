package lms.serviceImpl;

import lms.repositories.CountryRepository;
import lms.repositories.StateAndCityRepository;
import lms.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateAndCityRepository stateAndCityRepository;
    @Override
    public List<String> getAllCountryName(){
        return countryRepository.getAllCountryName();
    }
    @Override
    public List<String> getAllStateName(String countryName){
        return stateAndCityRepository.getAllStateName(countryRepository.findByCountryName(countryName).getId());
    }
    @Override
    public List<String> getAllCityName(String stateName){
        return stateAndCityRepository.getAllCityName(stateName);
    }
}
