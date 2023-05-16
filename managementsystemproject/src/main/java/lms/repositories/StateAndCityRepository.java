package lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lms.entities.StateAndCity;

import java.util.List;

/**
 * this interface will be used to connect {@link StateAndCity} from database
 *
 * @author ashutosh.baranwal , sparsh.gupta
 */

public interface StateAndCityRepository extends JpaRepository<StateAndCity, Long> {

    @Query(value = "select * from state_and_city where country_id=?1 and state_name=?2 and city_name=?3", nativeQuery = true)
    StateAndCity findStateCityId(Long countryId, String stateName, String cityName);

    @Query(value = "select state_name from state_and_city where country_id=?1 group By state_name", nativeQuery = true)
    List<String> getAllStateName(Long CountryId);

    @Query(value = "select city_name from state_and_city where state_name=?1", nativeQuery = true)
    List<String> getAllCityName(String stateName);

}
