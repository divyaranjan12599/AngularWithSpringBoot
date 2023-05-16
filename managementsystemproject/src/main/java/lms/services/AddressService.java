package lms.services;

import java.util.List;

public interface AddressService {

    public List<String> getAllCountryName();
    public List<String> getAllStateName(String countryName);
    public List<String> getAllCityName(String stateName);
}
