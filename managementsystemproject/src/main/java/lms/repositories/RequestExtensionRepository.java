package lms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lms.entities.BookIssueDetails;
import lms.entities.RequestExtension;

@Repository
public interface RequestExtensionRepository extends JpaRepository<RequestExtension, Long> {

    public List<RequestExtension> findByissueId(BookIssueDetails bookIssueDetails);
}
