package lms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long countryId;

    private String countryName;

    private String countryPhoneCode;

    public Country() {

    }

    public Country(long id, String countryName, String countryPhoneCode) {
        this.countryId = id;
        this.countryName = countryName;
        this.countryPhoneCode = countryPhoneCode;
    }

    public String getCountryPhoneCode() {
        return countryPhoneCode;
    }

    public void setCountryPhoneCode(String countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }

    public long getId() {
        return countryId;
    }

    public void setId(long id) {
        this.countryId = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
