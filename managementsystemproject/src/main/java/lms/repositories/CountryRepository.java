package lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lms.entities.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * this interface will be used to connect {@link Country} from database
 *
 * @author ashutosh.baranwal , sparsh.gupta
 */
@EnableJpaRepositories
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByCountryName(String countryName);
    @Query(value="select country_name from country",nativeQuery = true)
    public List<String> getAllCountryName();


}