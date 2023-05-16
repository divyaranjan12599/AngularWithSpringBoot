package lms.repositories;

import java.util.List;

import lms.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import lms.entities.BookDetails;
import lms.entities.BookIssueDetails;

public interface BookIssueRepository extends JpaRepository<BookIssueDetails, Long> {

    List<BookIssueDetails> findByUserDetail(UserDetails userDetail);
    
    List<BookIssueDetails> findByBookDetailsAndUserDetail(BookDetails bookDetails, UserDetails userDetails);

}
