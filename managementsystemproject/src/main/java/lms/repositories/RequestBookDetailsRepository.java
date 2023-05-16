package lms.repositories;

import java.util.List;
import java.util.Optional;

import lms.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lms.entities.RequestBookDetails;

/**
 * this interface will be used to connect {@link RequestBookDetails} from database
 *
 * @author ashutosh.baranwal
 */

@EnableJpaRepositories
public interface RequestBookDetailsRepository extends JpaRepository<RequestBookDetails, Long> {

    List<RequestBookDetails> findByUserDetail(Optional<UserDetails> findById);

}
